package com.fannverse.MatchFetcher.Controller;


import com.fannverse.MatchFetcher.Service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/otp")
public class OTPVerify {

    @Autowired
    private OTPService otpService;

    // API to send OTP
    @PostMapping("/send")
    public String sendOtp(@RequestParam String phoneNumber) {
        String status = otpService.sendOtp(phoneNumber);
        return "OTP sent: " + status;
    }

    // API to verify OTP
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otpCode) {
        String status = otpService.verifyOtp("+"+phoneNumber, otpCode);
        return "OTP verification status: " + status;
    }
}

