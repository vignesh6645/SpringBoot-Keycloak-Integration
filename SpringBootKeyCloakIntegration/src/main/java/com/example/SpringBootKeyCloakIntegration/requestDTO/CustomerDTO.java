package com.example.SpringBootKeyCloakIntegration.requestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private String email;
    private String pwd;
    private String role;
}
