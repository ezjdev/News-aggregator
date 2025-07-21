package com.colvir.bootcamp.news.supplier.og.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OgNewsCategoriesDto {

    @JsonProperty("US")
    private List<OgNewsDto> us;
    @JsonProperty("World")
    private List<OgNewsDto> world;
    @JsonProperty("Business")
    private List<OgNewsDto> business;
    @JsonProperty("Technology")
    private List<OgNewsDto> technology;
    @JsonProperty("Entertainment")
    private List<OgNewsDto> entertainment;
    @JsonProperty("Sports")
    private List<OgNewsDto> sports;
    @JsonProperty("Science")
    private List<OgNewsDto> science;
    @JsonProperty("Health")
    private List<OgNewsDto> health;

}
