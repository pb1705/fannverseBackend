package com.fannverse.MatchFetcher.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Toss {
    @JsonProperty("text")
    private String text;

    @JsonProperty("winner")
    private Integer winner;

    @JsonProperty("decision")
    private Integer decision;
}
