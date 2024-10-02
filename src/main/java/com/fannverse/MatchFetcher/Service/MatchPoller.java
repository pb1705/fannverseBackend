package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Models.Match;
import com.fannverse.MatchFetcher.Models.MatchPollerException;
import com.fannverse.MatchFetcher.Repository.MatchRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.EventListener;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@Service
@Component
@ConfigurationProperties(prefix = "entitysport.matches")
public class MatchPoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchPoller.class);

    @Autowired
    private RedisService redisService;
    private ScheduledExecutorService scheduler;
    private String url;

    @Autowired
    private MatchRepository matchRepository;

    MatchPoller(){
        scheduler = new ScheduledThreadPoolExecutor(5);
    }


    private List<Match> getMatches(){
        try{

            RestTemplate restTemplate = new RestTemplate();
            String matches = restTemplate.getForObject(url,String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(matches);


            JsonNode itemsNode = rootNode.path("response").path("items");
            LOGGER.info("Matches Fetched from the api endpoint");
            LOGGER.debug(matches);
            List<Match> matchesList = new ArrayList<>();
            matchesList=objectMapper.readValue(itemsNode.toString(), new TypeReference<List<Match>>() {});
            if(matchesList!=null) {
                redisService.setList("matches",matchesList);
            }
            return matchesList;


        } catch (Exception e) {

            LOGGER.error("Unable to get Matches!"+e.getMessage());
            return new ArrayList<>();
        }
    }

    private void dumpMatches(){
        try{
            matchRepository.saveAll(getMatches());
        }
        catch(Exception e){
            LOGGER.error("Unable to dump Matches!"+e.getMessage());
        }
    }


    public List<Match>fetchMatches(){
        try {
            List<Match> matches = redisService.getList("matches", Match.class);
            LOGGER.info(matches.toString());
            if(matches!=null) {
                LOGGER.info("Matches Fetched from the redis endpoint");
                return matches;
            }
            else{
                matches=matchRepository.findAll();
                LOGGER.info("Matches Fetched from the db endpoint");
                redisService.setList("matches",matches);
                return matches;
            }
        }
        catch(Exception e){
            LOGGER.error("Unable to get Matches!"+e);
            throw new MatchPollerException("Unable to fetch Matches!"+e.getMessage());
        }


    }

    @EventListener(ApplicationReadyEvent.class)
    protected void pollMatches(){
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                dumpMatches();
            }
        },0,30, TimeUnit.SECONDS);
    }
}
