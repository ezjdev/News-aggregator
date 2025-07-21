package com.colvir.bootcamp.news.server.service;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import com.colvir.bootcamp.news.common.service.NewsIdGenerator;
import com.colvir.bootcamp.news.server.repository.NewsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.colvir.bootcamp.news.common.constant.NewsConst.NEWS_QUEUE_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsConsumerService {

    private final ObjectMapper objectMapper;
    private final NewsRepository newsRepository;
    private final NewsIdGenerator idGenerator;

    @JmsListener(destination = NEWS_QUEUE_NAME)
    public void onMessage(Message message) throws JMSException, JsonProcessingException {
        if (message instanceof TextMessage textMessage) {
            Optional.of(objectMapper.readValue(textMessage.getText(), NewsDto.class))
                    .map(it -> {
                        log.atInfo().log("News received: {}", it);
                        return it.setId(idGenerator.generateNewsId(it));
                    })
                    .filter(it -> !newsRepository.existsById(it.getId()))
                    .ifPresent(it -> {
                        save(it);
                        log.atInfo().log("News saved with id: {}", it.getId());
                    });
        }
    }

    private void save(NewsDto newsDto) {
        newsRepository.save(newsDto);
    }

    public List<NewsDto> getNewsList(Pageable pageable) {
        return newsRepository.findAll(pageable).getContent();
    }

    public Optional<NewsDto> getById(String id) {
        return newsRepository.findById(id);
    }
}
