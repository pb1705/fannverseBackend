package com.fannverse.MatchFetcher.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Player {

    String name;
    @Id
    String playerId;
}
