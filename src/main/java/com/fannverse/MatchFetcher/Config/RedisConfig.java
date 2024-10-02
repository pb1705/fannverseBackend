package com.fannverse.MatchFetcher.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.*;


@Configuration
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class RedisConfig {

    private String host;
    private String password;
    private Integer port;
    private String username;

    @Bean
    public JedisPooled jedisPooled() {
        HostAndPort address = new HostAndPort(host, port);
        ConnectionPoolConfig poolConfig = new ConnectionPoolConfig();
        poolConfig.setJmxEnabled(false);
        JedisClientConfig config = DefaultJedisClientConfig.builder().user(username)
                .password(password)
                .build();
        return new JedisPooled(poolConfig,address,config);
    }
}
