package com.colvir.bootcamp.news.supplier.og.service;

import com.colvir.bootcamp.news.common.service.NewsIdGenerator;
import com.colvir.bootcamp.news.supplier.og.feign.NewsSupplierOgClient;
import com.colvir.bootcamp.news.supplier.og.dto.OgNewsCategoriesDto;
import com.colvir.bootcamp.news.supplier.og.mapper.OgNewsMapper;
import com.colvir.bootcamp.news.common.dto.NewsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.colvir.bootcamp.news.common.constant.NewsConst.NEWS_QUEUE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OgNewSupplierService {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final OgNewsMapper ogNewsMapper;
    private final NewsSupplierOgClient newsSupplierOgClient;
    private final NewsIdGenerator newsIdGenerator;
    private static final Map<String, Instant> loadedNewsMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        getNews();
    }

    @Scheduled(fixedRate = 60_000)
    public void scheduled() {
        getNews();
    }

    public void sendNews(NewsDto newsDto) {
        try {
            jmsTemplate.convertAndSend(NEWS_QUEUE_NAME
                    , objectMapper.writeValueAsString(newsDto)
                    , message -> {
                        message.setStringProperty("source", newsDto.getSource());
                        return message;
                    }
            );
            log.atInfo().log("News \"{}\" sent", newsDto.getTitle());
            loadedNewsMap.put(newsDto.getId(), Instant.now());
        } catch (JsonProcessingException e) {
            log.atError().log("""
                    Error received: {}
                    Error while sending new news: {}
                    """
                    , e.getMessage(), newsDto);
        }
    }

    public void getNews() {
        log.atInfo().log("Requesting news from OG");
        OgNewsCategoriesDto newsList = null;
        try {
            newsList = newsSupplierOgClient.getNews();
            log.atTrace().log("Received {} business news", newsList.getBusiness().size());

            newsList.getBusiness().stream()
                    .map(ogNewsMapper::mapToNewsDto)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map(it -> it.setId(newsIdGenerator.generateNewsId(it)))
                    .filter(it -> !loadedNewsMap.containsKey(it.getId()))
                    .forEach(this::sendNews);
        } catch (Exception ex) {
            log.atError().log("OG service error", ex);
        }
    }

}
