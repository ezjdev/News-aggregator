package com.colvir.bootcamp.news.server.controller;

import com.colvir.bootcamp.news.server.service.NewsConsumerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsAggregatorController.class)
@DisplayName("The server controller should be able to ")
class NewsAggregatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NewsConsumerService consumerService;

    @Test
    @DisplayName("be able to return a list of news")
    void getAllPlaylists() throws Exception {
        this.mockMvc.perform(get("/api/v1/news"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}