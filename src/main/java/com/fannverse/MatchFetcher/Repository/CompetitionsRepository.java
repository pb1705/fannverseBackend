package com.fannverse.MatchFetcher.Repository;

import com.fannverse.MatchFetcher.Models.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionsRepository extends MongoRepository<Competition, Long> {
}
