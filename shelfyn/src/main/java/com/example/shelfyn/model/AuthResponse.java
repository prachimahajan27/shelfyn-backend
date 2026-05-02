package com.example.shelfyn.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthResponse {

    public String accessToken;
    public String refreshToken;
    public String email;


}