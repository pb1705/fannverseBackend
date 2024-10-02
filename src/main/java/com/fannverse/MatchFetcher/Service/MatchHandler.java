package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Models.Match;
import com.fannverse.MatchFetcher.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchHandler {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RedisService redisService;

    public  Long addMatch(Match match){
        Match savedMatch =  matchRepository.save(match);
        return  savedMatch.getMatchId();
    }
    public  Match editMatch(Match match){
        Match currMatch = matchRepository.findById(match.getMatchId()).orElse(null);
        if(currMatch == null){
            throw new RuntimeException("Match not found");
        }
        else{
            matchRepository.save(match);
        }
        return match;
    }

    public void deleteMatch(Long id){
        Match match = matchRepository.findById(id).orElse(null);
        if(match == null){
            throw new RuntimeException("No Such Match found");
        }
        matchRepository.deleteById(id);
    }

    public Match getMatch(Long id){
        Match match= matchRepository.findById(id).orElse(null);
        if(match == null){
            throw new RuntimeException("No Such Match found");
        }
        return match;
    }
}
