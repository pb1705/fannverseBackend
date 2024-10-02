package com.fannverse.MatchFetcher.Controller;

import com.fannverse.MatchFetcher.Models.Competition;
import com.fannverse.MatchFetcher.Service.CompetitionPoller;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Setter
@Getter
@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionInfoController {

    @Autowired
    private CompetitionPoller competitionPoller;


    @RequestMapping
    public List<Competition> getCompetitions() {

        return competitionPoller.getCompetitions();
    }

}
