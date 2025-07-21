package com.colvir.bootcamp.news.supplier.og.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OgNewsDto {

    private String link;
    private String og;
    private String source;
    @JsonProperty("source_icon")
    private String sourceIcon;
    private String title;

}
