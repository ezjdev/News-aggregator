package com.colvir.bootcamp.news.frontend.feign;

import com.colvir.bootcamp.news.common.web.NewsAggregatorApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "news-aggregator-server", url = "${news.api.url}", fallback = NewsAggregatorClientFallback.class)
public interface NewsAggregatorClient extends NewsAggregatorApi {
}
