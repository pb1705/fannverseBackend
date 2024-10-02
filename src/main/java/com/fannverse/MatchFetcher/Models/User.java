package com.fannverse.MatchFetcher.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "Users")
public class User {

        @Id
        @DBRef
        private String userId;

        private String username;
        private Integer contestsWon;
        private Integer totalContests;

        @DBRef
        private List<Match> matches;

        private Integer series;


        private List<String> contestIds;

        @DBRef
        private List<Team> teams;

        private String mobile;
        private String email;

        private Date dob;
        private Short gender;
        private String country;
        private String state;
        private Integer points;
        private String authToken;
        private Integer currentBalance;
        private Integer amountUnUtilized;
        private Integer winnings;
        private Integer discountBonus;

        private List<Transaction> transactions;


        private String referingUserId;

        private String referenceCode;
        private Boolean usernameVerified;
        private Boolean mobileVerified;
        private Boolean emailVerified;

        private Date createdAt;
}
