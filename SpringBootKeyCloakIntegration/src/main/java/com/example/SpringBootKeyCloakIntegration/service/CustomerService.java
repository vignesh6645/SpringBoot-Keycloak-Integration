package com.example.SpringBootKeyCloakIntegration.service;

import com.example.SpringBootKeyCloakIntegration.model.Customer;
import com.example.SpringBootKeyCloakIntegration.requestDTO.CustomerDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomerDetails(CustomerDTO customerDTO) throws Exception;

    List<Customer> getCustomerDetails();
}
