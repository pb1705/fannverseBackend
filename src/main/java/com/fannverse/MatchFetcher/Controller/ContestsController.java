package com.fannverse.MatchFetcher.Controller;

import com.fannverse.MatchFetcher.Models.Contests;
import com.fannverse.MatchFetcher.Service.ContestHandler;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
@RequestMapping("/api/v1/contests")
public class ContestsController {

    @Autowired
    private ContestHandler contestHaandler;

    @GetMapping("/")
    public List<Contests> contests() {
        return  contestHaandler.getContests();
    }
    @GetMapping("/{contestId}")
    public Contests contests(@PathVariable Long contestId) {
        return  contestHaandler.getContest(contestId);
    }

    @DeleteMapping("/{contestId}")
    public void deleteContest(@PathVariable Long contestId) {
         contestHaandler.deleteContest(contestId);
    }

    @PutMapping("/")
    @PostMapping("/")
    public String updateContest( @RequestBody Contests contest) {
            return contestHaandler.updateAddContest(contest);
    }

    @GetMapping("/{matchId}")
    public List<Contests> match(@PathVariable Long matchId) {
        return contestHaandler.getContestsByMatchId(matchId);
    }


}
