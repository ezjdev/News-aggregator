package com.colvir.bootcamp.cryptocurrency.binance.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

@Configuration
@EnableScheduling
public class BinanceConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ReactorNettyWebSocketClient reactorNettyWebSocketClient(){
        return new ReactorNettyWebSocketClient();
    }

}
