package com.fannverse.MatchFetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MatchFetcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchFetcherApplication.class, args);
	}

}
