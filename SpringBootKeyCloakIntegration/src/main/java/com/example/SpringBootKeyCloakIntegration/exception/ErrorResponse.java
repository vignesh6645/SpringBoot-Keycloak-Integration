package com.example.SpringBootKeyCloakIntegration.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorResponse {

    private int statusCode;

    private String statusMsg;
}
