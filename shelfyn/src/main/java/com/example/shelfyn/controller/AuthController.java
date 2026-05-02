package com.example.shelfyn.controller;

import com.example.shelfyn.Entity.User;

import com.example.shelfyn.Service.OtpService;
import com.example.shelfyn.model.*;
import com.example.shelfyn.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

//    private final AuthService service;
    private final OtpService otpservice;


//    @PostMapping("/signup")
//    public String signup(@RequestBody SignupRequest request) {
//        return service.signup(request);
//    }
//
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody LoginRequest request) {
//        return service.login(request);
//    }

    @PostMapping("/request-otp")
    public String requestOtp(@RequestBody OtpRequest request) {
        return otpservice.generateOtp(request.email);
    }

    @PostMapping("/verify-otp")
    public AuthResponse verifyOtp(@RequestBody VerifyOtpRequest request) {
        return otpservice.verifyOtp(request);
    }
    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {
        return otpservice.refreshToken(request.refreshToken);
    }
    @PostMapping("/logout")
    public String logout(@RequestBody RefreshRequest request) {
        otpservice.logout(request.getRefreshToken());
        return "Logged out";
    }

}
