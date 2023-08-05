package com.example.SpringBootKeyCloakIntegration.repository;

import com.example.SpringBootKeyCloakIntegration.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    List<Customer> findByEmail(String username);
}
