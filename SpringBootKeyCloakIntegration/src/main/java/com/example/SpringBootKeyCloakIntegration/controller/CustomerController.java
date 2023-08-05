package com.example.SpringBootKeyCloakIntegration.controller;

import com.example.SpringBootKeyCloakIntegration.model.Customer;
import com.example.SpringBootKeyCloakIntegration.requestDTO.CustomerDTO;
import com.example.SpringBootKeyCloakIntegration.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<String> saveCustomerDetails(@RequestBody CustomerDTO customerDTO) throws Exception{
        return ResponseEntity.ok(customerService.saveCustomerDetails(customerDTO));
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('SuperAdmin')")
    public ResponseEntity<List<Customer>> getCustomerDetails(){
        return ResponseEntity.ok(customerService.getCustomerDetails());
    }
}
