package com.fannverse.MatchFetcher.Controller;


import com.fannverse.MatchFetcher.Models.User;
import com.fannverse.MatchFetcher.Repository.UserRepository;
import com.fannverse.MatchFetcher.Service.JWTService;
import com.fannverse.MatchFetcher.Service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/otp")
public class OTPVerify {

    @Autowired
    private OTPService otpService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    // API to send OTP
    @PostMapping("/send")
    public String sendOtp(@RequestParam String phoneNumber) {
        String status = otpService.sendOtp(phoneNumber);
        return "OTP sent: " + status;
    }

    // API to verify OTP
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otpCode) throws Exception {
        String status = otpService.verifyOtp("+"+phoneNumber, otpCode);
        User user = userRepository.findByMobile(phoneNumber);
        System.out.println(user);
        if(user == null) {
            user = new User();
            user.setMobile(phoneNumber);
            user.setMobileVerified(true);
            user = userRepository.save(user);
        }
        if(status.equals("approved")){
            return jwtService.generateToken(user.getUserId());
        }
        throw new Exception("User not found!");
    }
}

