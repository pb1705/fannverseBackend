package com.fannverse.MatchFetcher.Service;

import com.fannverse.MatchFetcher.Config.TwilioConfiguration;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    private TwilioConfiguration twilioConfig;

    private static  final Logger LOGGER = LoggerFactory.getLogger(OTPService.class);
    // Method to send OTP
    public String sendOtp(String phoneNumber) {
        LOGGER.info("Sending OTP to " + phoneNumber);
        Verification verification = Verification.creator(
                twilioConfig.getVerifyServiceSid(),
                "+"+phoneNumber, // Recipient's phone number
                "sms" // Channel to send OTP (sms, call, email)
        ).create();

        return verification.getStatus();
    }

    // Method to verify OTP
    public String verifyOtp(String phoneNumber, String otpCode) {
        VerificationCheck verificationCheck = VerificationCheck.creator(
                        twilioConfig.getVerifyServiceSid())
                .setTo(phoneNumber)
                .setCode(otpCode)
                .create();

        return verificationCheck.getStatus(); // Will return "approved" if the OTP is valid
    }
}
