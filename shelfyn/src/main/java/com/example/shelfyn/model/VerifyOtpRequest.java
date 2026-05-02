package com.example.shelfyn.model;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    public String email;
    public String otp;
}