package com.colvir.bootcamp.news.server.config;

import com.colvir.bootcamp.news.common.service.NewsIdGenerator;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableMongoRepositories(basePackages = {"com.colvir.bootcamp.news.server.repository"})
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.mongodb.uri}")
    private String mongoUri;

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.applyConnectionString(new ConnectionString(mongoUri));
    }

    @Override
    @NonNull
    protected String getDatabaseName() {
        return mongoUri.replaceAll(".*/", "");
    }

    @Bean
    public NewsIdGenerator newsIdGenerator() {
        return new NewsIdGenerator();
    }
}
