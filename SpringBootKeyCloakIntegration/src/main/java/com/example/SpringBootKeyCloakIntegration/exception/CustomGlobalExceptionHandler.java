package com.example.SpringBootKeyCloakIntegration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> unAuthorized(AuthException ex){
        ErrorResponse errors = new ErrorResponse();
        errors.setStatusCode(ex.getStatusCode());
        errors.setStatusMsg(ex.getStatusMsg());
        return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> runTimeException(AuthException ex){
        ErrorResponse errors = new ErrorResponse();
        errors.setStatusCode(ex.getStatusCode());
        errors.setStatusMsg(ex.getStatusMsg());
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
