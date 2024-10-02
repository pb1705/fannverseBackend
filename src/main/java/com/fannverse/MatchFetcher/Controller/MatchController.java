package com.fannverse.MatchFetcher.Controller;


import com.fannverse.MatchFetcher.Models.Match;
import com.fannverse.MatchFetcher.Service.MatchHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

    @Autowired
    private MatchHandler matchHandler;

    @GetMapping("/{matchId}")
    public Match getMatch(@PathVariable Long matchId) {
        return matchHandler.getMatch(matchId);
    }

    @PutMapping("/")
    public Match editMatch(@RequestBody Match newMatch) {
        return matchHandler.editMatch(newMatch);
    }

    @DeleteMapping("/{matchId}")
    public void deleteMatch(@PathVariable Long matchId) {
        matchHandler.deleteMatch(matchId);
    }

    @PostMapping("/")
    public Long addMatch(@RequestBody Match newMatch) {
        System.out.println("Request to add Match" + newMatch.toString());
        return matchHandler.addMatch(newMatch);
    }

}
