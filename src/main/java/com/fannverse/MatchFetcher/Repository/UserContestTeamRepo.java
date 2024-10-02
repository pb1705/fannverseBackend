package com.fannverse.MatchFetcher.Repository;

import com.fannverse.MatchFetcher.Models.UserContestTeam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContestTeamRepo extends MongoRepository<UserContestTeam, String> {
}
