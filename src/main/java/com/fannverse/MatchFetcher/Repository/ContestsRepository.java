package com.fannverse.MatchFetcher.Repository;

import com.fannverse.MatchFetcher.Models.Contests;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContestsRepository extends MongoRepository<Contests,String> {

    public Iterable<Contests> findByMatchId(Long matchId);
}
