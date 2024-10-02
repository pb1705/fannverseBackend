package com.fannverse.MatchFetcher.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Contests")
public class Contests {

    @Id
    private String id;

    private String type;
    private String variation;

    @DBRef
    private List<User> winners;

    @DBRef
    private Long matchId;
    private Integer prizePool;
    private Integer entryFee;
    private Integer totalSpots;
    private Integer joinedSpots;
    private Integer megaPrize;
    private Integer teamsTotal;
}
