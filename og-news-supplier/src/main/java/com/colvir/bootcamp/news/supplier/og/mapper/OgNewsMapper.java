package com.colvir.bootcamp.news.supplier.og.mapper;

import com.colvir.bootcamp.news.common.dto.NewsDto;
import com.colvir.bootcamp.news.supplier.og.dto.OgNewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

import static com.colvir.bootcamp.news.supplier.og.config.JmsConfig.MAGAZINE;

@Component
@RequiredArgsConstructor
public class OgNewsMapper {

    public Optional<NewsDto> mapToNewsDto(OgNewsDto ogNewsDto) {
        return Optional.ofNullable(ogNewsDto)
                .map(it ->
                        NewsDto.builder()
                                .link(it.getLink())
                                .title(it.getTitle())
                                .source(it.getSource())
                                .magazine(MAGAZINE)
                                .publishDate(Instant.now())
                                .imageUrl(it.getSourceIcon())
                                .build()
                );
    }

}
