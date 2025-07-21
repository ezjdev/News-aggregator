package com.colvir.bootcamp.news.server.controller;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import com.colvir.bootcamp.news.common.web.NewsAggregatorApi;
import com.colvir.bootcamp.news.server.service.NewsConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
public class NewsAggregatorController implements NewsAggregatorApi {

        private final NewsConsumerService newsConsumerService;

        @GetMapping
        public List<NewsDto> getNewsList(Integer page, Integer size) {
            return newsConsumerService.getNewsList(PageRequest.of(page, size));
        }

        @GetMapping("/{id}")
        public ResponseEntity<NewsDto> getById(@PathVariable String id) {
            return newsConsumerService.getById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

}
