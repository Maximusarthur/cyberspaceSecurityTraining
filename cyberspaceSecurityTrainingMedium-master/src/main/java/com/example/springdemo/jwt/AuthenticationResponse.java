package com.example.springdemo.jwt;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;
}
