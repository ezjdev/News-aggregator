package com.colvir.bootcamp.news.supplier.og;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OgNewsSupplier {

    public static void main(String[] args) {
        SpringApplication.run(OgNewsSupplier.class, args);
    }

}
