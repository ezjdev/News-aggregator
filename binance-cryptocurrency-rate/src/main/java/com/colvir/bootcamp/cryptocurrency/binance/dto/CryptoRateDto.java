package com.colvir.bootcamp.cryptocurrency.binance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoRateDto {

    @JsonProperty("s")
    private String symbol;
    @JsonProperty("p")
    private double price;
    @JsonProperty("E")
    private long timestamp;

}
