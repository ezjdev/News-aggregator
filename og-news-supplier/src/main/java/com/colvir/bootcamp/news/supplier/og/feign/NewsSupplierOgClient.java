package com.colvir.bootcamp.news.supplier.og.feign;

import com.colvir.bootcamp.news.supplier.og.dto.OgNewsCategoriesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "google-ok-surf-client", url = "${news.api.url}")
public interface NewsSupplierOgClient {

    @GetMapping(value = "/news-feed", consumes = "application/json")
    OgNewsCategoriesDto getNews();

}
