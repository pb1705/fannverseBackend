package com.fannverse.MatchFetcher.Repository;

import com.fannverse.MatchFetcher.Models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends MongoRepository<Match,Long> {
}
