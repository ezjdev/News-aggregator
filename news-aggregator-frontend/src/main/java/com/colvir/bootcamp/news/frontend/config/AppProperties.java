package com.colvir.bootcamp.news.frontend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private int totalPages;
    private int pageSize;

}
