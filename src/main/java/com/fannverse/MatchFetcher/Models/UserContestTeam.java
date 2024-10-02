package com.fannverse.MatchFetcher.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class UserContestTeam {

    @Id
    public String id;

    public List<Player> playerList;

    Long captainId;

    Long viceCaptainId;

    @DBRef
    String contestId;

    @DBRef
    Long matchId;
}
