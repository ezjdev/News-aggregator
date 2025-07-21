package com.colvir.bootcamp.news.supplier.og.config;

import com.colvir.bootcamp.news.common.service.NewsIdGenerator;
import com.colvir.bootcamp.news.supplier.og.mapper.OgNewsMapper;
import feign.codec.Decoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJms
@Configuration
@EnableScheduling
public class JmsConfig {

    public static final String MAGAZINE = "OG";

    @Bean
    public Decoder feignDecoder() {
        return new SpringDecoder(HttpMessageConverters::new);
    }

    @Bean
    OgNewsMapper ogNewsMapper() {
        return new OgNewsMapper();
    }

    @Bean
    NewsIdGenerator newsIdGenerator(){
        return new NewsIdGenerator();
    }

}
