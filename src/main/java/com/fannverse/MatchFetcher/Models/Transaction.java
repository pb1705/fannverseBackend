package com.fannverse.MatchFetcher.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transacctions")
public class Transaction {


    @Id
    private String id;


    private String paymentToken;

    private String last4Digits;

    private String cardHolderName;

    private String expiryDate;
    private String transactionId;
    private String paymentMethod;
    private Double amount;
    private String currency;

    private String createdAt;

}
