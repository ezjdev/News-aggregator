package com.colvir.bootcamp.news.frontend.service;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import com.colvir.bootcamp.news.frontend.feign.NewsAggregatorClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsAggregatorService {

    private final NewsAggregatorClient newsAggregatorClient;

    public List<NewsDto> newsList(Integer page, Integer size) {
        log.info("Getting news list");
        return newsAggregatorClient.getNewsList(page, size);
    }
}
