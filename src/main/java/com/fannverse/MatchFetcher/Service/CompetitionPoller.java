package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Models.Competition;
import com.fannverse.MatchFetcher.Repository.CompetitionsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Getter
@Setter
@Service
@Component
@ConfigurationProperties(prefix = "entitysport.competitions")
public class CompetitionPoller {

    private String url;

    private ScheduledExecutorService scheduler;
    CompetitionPoller(){
        scheduler= Executors.newScheduledThreadPool(2);
    }
    @Autowired
    private CompetitionsRepository competitionsRepository;
    private static Logger logger = Logger.getLogger(CompetitionPoller.class.getName());





    private void dumpCompetitions() {

        List<Competition> competitions = pollCompetitions();
        competitionsRepository.saveAll(competitions);
    }

    public List<Competition> pollCompetitions() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);


            JsonNode itemsNode = rootNode.path("response").path("items");

            return objectMapper.readValue(itemsNode.toString(), new TypeReference<List<Competition>>() {});


        } catch (Exception e) {
            logger.severe("Error getting matches"+e.getMessage());
            throw  new RuntimeException("Error getting matches"+e.getMessage());
        }

    }

    public  List<Competition>getCompetitions() {
        return competitionsRepository.findAll();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startPolling() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                pollCompetitions();
            }
        },1,5, TimeUnit.MINUTES);
    }

}
