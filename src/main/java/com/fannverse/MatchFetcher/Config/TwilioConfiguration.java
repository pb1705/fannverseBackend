package com.fannverse.MatchFetcher.Config;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.verifyServiceSid}")
    private String verifyServiceSid;

    @PostConstruct
    public void twilioInitializer() {
        Twilio.init(accountSid, authToken);
    }

    public String getVerifyServiceSid() {
        return verifyServiceSid;
    }

}
