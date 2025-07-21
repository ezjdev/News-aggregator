package com.colvir.bootcamp.news.common.web;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface NewsAggregatorApi {

    @GetMapping
    List<NewsDto> getNewsList(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "30") Integer size
    );

    @GetMapping("/{id}")
     ResponseEntity<NewsDto> getById(@PathVariable String id);
}
