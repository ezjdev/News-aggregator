package com.colvir.bootcamp.news.frontend.controller;

import com.colvir.bootcamp.news.frontend.service.NewsAggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NewsFrontendController {

    public static final String NEWS_LIST = "main";
    public static final int TOTAL_PAGES = 5;
    public static final int ITEMS_PER_PAGE = 10;
    public static final String DEFAULT_PAGE_NUM = "1";

    private final NewsAggregatorService newsAggregatorService;

    @GetMapping
    public String listItems(@RequestParam(defaultValue = DEFAULT_PAGE_NUM) int page, Model model) {
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", TOTAL_PAGES);
        model.addAttribute("newsList", newsAggregatorService.newsList(page, ITEMS_PER_PAGE));
        return NEWS_LIST;
    }

}
