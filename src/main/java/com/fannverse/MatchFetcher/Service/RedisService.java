package com.fannverse.MatchFetcher.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPooled;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {

    @Autowired
    private JedisPooled jedisPooled;

    private  final static Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T>void setList(String key,List<T> objects){
            try{

                String json = objectMapper.writeValueAsString(objects);
                jedisPooled.set(key,json);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

    }
    public <T>List<T> getList(String key,Class<T>clazz){

        try {

            String json = jedisPooled.get(key);
            if(json == null){
                return new ArrayList<>();
            }
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
