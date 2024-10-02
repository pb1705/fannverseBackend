package com.fannverse.MatchFetcher.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Venue {
    @JsonProperty("venue_id")
    private String venueId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("country")
    private String country;

    @JsonProperty("timezone")
    private String timezone;
}