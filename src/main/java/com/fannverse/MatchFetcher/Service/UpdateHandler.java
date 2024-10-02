package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Repository.ContestsRepository;
import com.fannverse.MatchFetcher.Repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class UpdateHandler {

    private ScheduledExecutorService updateScheduler;

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateHandler.class);
    @Autowired
    private RedisService redisService;

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ContestsRepository contestsRepository;

    UpdateHandler(){
        updateScheduler= new ScheduledThreadPoolExecutor(1);
    }

    public void updateRedis(){
        try {
            redisService.setList("matches", matchRepository.findAll());
            redisService.setList("contests", contestsRepository.findAll());
            LOGGER.info("Updated Redis with latest info");
        }
        catch (Exception e){
            LOGGER.error(e.toString());
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public  void runScheduler(){
        try{
            updateScheduler.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    updateRedis();
                }
            },0,5, TimeUnit.MINUTES);
        }
        catch (Exception e){
            LOGGER.error(e.toString());
        }
    }
}
