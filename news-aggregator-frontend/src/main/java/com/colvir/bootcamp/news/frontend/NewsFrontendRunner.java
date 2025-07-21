package com.colvir.bootcamp.news.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class NewsFrontendRunner {
    public static void main(String[] args) {
        SpringApplication.run(NewsFrontendRunner.class, args);
    }
}
