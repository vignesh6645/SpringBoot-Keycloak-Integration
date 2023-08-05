package com.example.SpringBootKeyCloakIntegration.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthException extends RuntimeException {

    private int statusCode;

    private String statusMsg;

    public AuthException(int statusCode,String statusMsg){
        super(statusMsg);
        this.statusCode =statusCode;
        this.statusMsg=statusMsg;
    }
}
