package com.colvir.bootcamp.news.frontend.controller;

import com.colvir.bootcamp.news.frontend.config.AppProperties;
import com.colvir.bootcamp.news.frontend.service.NewsAggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NewsFrontendController {

    private final AppProperties appProperties;

    public static final String NEWS_LIST = "main";
    public static final String DEFAULT_PAGE_NUM = "1";

    private final NewsAggregatorService newsAggregatorService;

    @GetMapping
    public String listItems(@RequestParam(defaultValue = DEFAULT_PAGE_NUM) int page, Model model) {
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", appProperties.getTotalPages());
        model.addAttribute("newsList", newsAggregatorService.newsList(page, appProperties.getPageSize()));
        return NEWS_LIST;
    }

}
