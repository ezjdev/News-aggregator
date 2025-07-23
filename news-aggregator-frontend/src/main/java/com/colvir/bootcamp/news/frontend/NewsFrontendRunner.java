package com.colvir.bootcamp.news.frontend;

import com.colvir.bootcamp.news.frontend.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class NewsFrontendRunner {
    public static void main(String[] args) {
        SpringApplication.run(NewsFrontendRunner.class, args);
    }
}
