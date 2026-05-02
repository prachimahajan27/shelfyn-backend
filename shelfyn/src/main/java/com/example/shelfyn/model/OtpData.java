package com.example.shelfyn.model;

import lombok.Data;

@Data
public class OtpData {
    String otp;
    long expiryTime;
}