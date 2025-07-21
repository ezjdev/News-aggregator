package com.colvir.bootcamp.news.frontend.service;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import com.colvir.bootcamp.news.frontend.feign.NewsAggregatorClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsAggregatorService {

    private final NewsAggregatorClient newsAggregatorClient;

    public List<NewsDto> newsList(Integer page, Integer size) {
        return newsAggregatorClient.getNewsList(page, size);
    }
}
