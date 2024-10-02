package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Models.Contests;
import com.fannverse.MatchFetcher.Models.Player;
import com.fannverse.MatchFetcher.Models.User;
import com.fannverse.MatchFetcher.Models.UserContestTeam;
import com.fannverse.MatchFetcher.Repository.ContestsRepository;
import com.fannverse.MatchFetcher.Repository.UserContestTeamRepo;
import com.fannverse.MatchFetcher.Repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@Service
public class ContestHandler {

    @Autowired
    private ContestsRepository contestsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserContestTeamRepo userContestTeamRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Contests> getContests(){
        return contestsRepository.findAll();
    }

    public Contests getContest(Long contestId) {
        return contestsRepository.findById(contestId.toString()).orElse(null);
    }

    public String updateAddContest(Contests contest) {
           Contests contests= contestsRepository.save(contest);
           return  contests.getId();
    }
    public void deleteContest(Long contestId) {
            contestsRepository.deleteById(contestId.toString());
    }

    public List<Contests> getContestsByMatchId(Long matchId) {
         return  (List<Contests>) contestsRepository.findByMatchId(matchId);
    }
    public void registerInContest(UserContestTeam userContestTeam,String userId) {
         String contestId = userContestTeam.getContestId();
        User user = userRepository.findById(userId).orElse(null);
        if(user!=null){
            List<String>contestIds=user.getContestIds();
            contestIds.add(contestId);
            user.setContestIds(contestIds);
            userRepository.save(user);
            userContestTeamRepository.save(userContestTeam);

            String tempDocument = userContestTeam.getContestId() + userContestTeam.getMatchId().toString();
            if(!mongoTemplate.collectionExists(tempDocument)){
                mongoTemplate.createCollection(tempDocument);
            }
            userContestTeam.getPlayerList().forEach(player->{
                Query query= new Query(Criteria.where("playerId").is(player.getPlayerId()));
                Update update = new Update().addToSet("userIds",userId);
                mongoTemplate.upsert(query, update, tempDocument);
            });
        }
        else{
            throw  new RuntimeException("User not found");
        }
    }






}
