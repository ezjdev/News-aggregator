package com.colvir.bootcamp.news.frontend.controller;


import com.colvir.bootcamp.news.frontend.service.NewsAggregatorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(value = NewsFrontendController.class)
@Import(NewsFrontendControllerTest.DisableSecurityConfig.class)
@DisplayName("The frontend controller should be able to ")
class NewsFrontendControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private NewsAggregatorService newsAggregatorService;

    @Test
    @DisplayName("be able to display a list of news")
    public void shouldAbleToGetDepartmentById() throws Exception {
        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    String body = response.getResponseBody();
                    assert body != null;
                });
    }

    @TestConfiguration
    static class DisableSecurityConfig {
        @Bean
        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            return http
                    .csrf(ServerHttpSecurity.CsrfSpec::disable) // Updated for CSRF
                    .authorizeExchange(exchanges -> exchanges
                            .pathMatchers("*")
                            .permitAll()
                            .anyExchange()
                            .authenticated()
                    ).build();
        }
    }

}