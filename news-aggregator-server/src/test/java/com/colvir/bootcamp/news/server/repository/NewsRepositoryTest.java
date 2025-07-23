package com.colvir.bootcamp.news.server.repository;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
@DisplayName("The repository for working with news should ")
class NewsRepositoryTest {

    public static final String TEST = "test";
    @Autowired
    private NewsRepository repository;

    @Test
    @DisplayName("be able to save news")
    void shouldSaveNewEmployee() {
        NewsDto newsForSave = NewsDto.builder().id(TEST).link(TEST).category(TEST).build();
        repository.save(newsForSave);
        NewsDto newsFromRepository = repository.findById(TEST).orElse(null);
        assertThat(newsForSave).isEqualTo(newsFromRepository);
    }

}