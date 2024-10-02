package com.fannverse.MatchFetcher.Repository;


import com.fannverse.MatchFetcher.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
