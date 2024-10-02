package com.fannverse.MatchFetcher.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    @Id
    @JsonProperty("match_id")
    private Long matchId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("short_title")
    private String shortTitle;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("match_number")
    private String matchNumber;

    @JsonProperty("format")
    private Integer format;

    @JsonProperty("format_str")
    private String formatStr;

    @JsonProperty("status")
    private String status;

    @JsonProperty("status_str")
    private String statusStr;

    @JsonProperty("status_note")
    private String statusNote;

    @JsonProperty("verified")
    private Boolean verified;

    @JsonProperty("pre_squad")
    private Boolean preSquad;

    @JsonProperty("odds_available")
    private Boolean oddsAvailable;

    @JsonProperty("game_state")
    private Integer gameState;

    @JsonProperty("game_state_str")
    private String gameStateStr;

    @JsonProperty("domestic")
    private String domestic;

    @JsonProperty("competition")
    private Competition competition;

    @JsonProperty("teama")
    private Team teama;

    @JsonProperty("teamb")
    private Team teamb;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_start")
    private Date dateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_end")
    private Date dateEnd;

    @JsonProperty("timestamp_start")
    private Long timestampStart;

    @JsonProperty("timestamp_end")
    private Long timestampEnd;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_start_ist")
    private Date dateStartIst;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date_end_ist")
    private Date dateEndIst;

    @JsonProperty("venue")
    private Venue venue;

    @JsonProperty("umpires")
    private String umpires;

    @JsonProperty("referee")
    private String referee;

    @JsonProperty("equation")
    private String equation;

    @JsonProperty("live")
    private String live;

    @JsonProperty("result")
    private String result;

    @JsonProperty("result_type")
    private Integer resultType;

    @JsonProperty("win_margin")
    private String winMargin;

    @JsonProperty("winning_team_id")
    private Integer winningTeamId;

    @JsonProperty("commentary")
    private Integer commentary;

    @JsonProperty("wagon")
    private Integer wagon;

    @JsonProperty("latest_inning_number")
    private Integer latestInningNumber;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("presquad_time")
    private Date presquadTime;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("verify_time")
    private Date verifyTime;

    @JsonProperty("match_dls_affected")
    private Boolean matchDlsAffected;

    @JsonProperty("toss")
    private Toss toss;

    @JsonProperty("isExpired")
    private Boolean isExpired;

}

