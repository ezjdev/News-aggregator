package com.colvir.bootcamp.news.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@Accessors(chain = true)
public class NewsDto {

    private String id;
    private String category;
    private String title;
    private String source;
    private String magazine;
    private String link;
    private String imageUrl;
    private Instant publishDate;
    private List<String> tags;

}
