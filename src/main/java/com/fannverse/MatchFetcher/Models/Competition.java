package com.fannverse.MatchFetcher.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Competition {

    @Id
    @JsonProperty("cid")
    private Long competitionId;

    @JsonProperty("title")
    private String competitionName;

    @JsonProperty("abbr")
    private String competitionAbbreviation;

    @JsonProperty("type")
    private String competitionType;

    @JsonProperty("match_format")
    private String matchFormat;

    @JsonProperty("season")
    private String competitionSeason;

    @JsonProperty("status")
    private String competitionStatus;

    @JsonProperty("datestart")
    private Date competitionDateStart;

    @JsonProperty("dateend")
    private Date competitionDateEnd;

    @JsonProperty("country")
    private String competitionCountry;

    @JsonProperty("total_matches")
    private int totalMatches;

    @JsonProperty("total_rounds")
    private int totalRounds;

    @JsonProperty("total_teams")
    private int totalTeams;

    @JsonProperty("category")
    private String competitionCategory;

    @JsonProperty("game_format")
    private String gameFormat;
}
