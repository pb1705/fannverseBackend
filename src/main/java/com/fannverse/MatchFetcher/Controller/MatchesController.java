package com.fannverse.MatchFetcher.Controller;


import com.fannverse.MatchFetcher.Models.Match;
import com.fannverse.MatchFetcher.Service.MatchPoller;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Setter
@Getter
@RestController
@RequestMapping("/api/v1/matches/")
public class MatchesController {

    @Autowired
    private MatchPoller matchPoller;

    @RequestMapping
    public ResponseEntity<List<Match>>getMatches(){
        try {
            return new ResponseEntity<>(matchPoller.fetchMatches(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
