package com.colvir.bootcamp.news.supplier.og.feign;

import com.colvir.bootcamp.news.supplier.og.dto.OgNewsCategoriesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "googleOkSurfClient", url = "https://ok.surf/api/v1/")
public interface NewsSupplierOgClient {

    @GetMapping(value = "/news-feed", consumes = "application/json")
    OgNewsCategoriesDto getNews();

}
