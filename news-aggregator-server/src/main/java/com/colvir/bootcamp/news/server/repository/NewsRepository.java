package com.colvir.bootcamp.news.server.repository;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<NewsDto, String> {
}
