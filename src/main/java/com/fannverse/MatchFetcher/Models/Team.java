package com.fannverse.MatchFetcher.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Team {
    @JsonProperty("team_id")
    private Integer teamId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("short_name")
    private String shortName;

    @JsonProperty("logo_url")
    private String logoUrl;

    @JsonProperty("scores_full")
    private String scoresFull;

    @JsonProperty("scores")
    private String scores;

    @JsonProperty("overs")
    private String overs;
}

