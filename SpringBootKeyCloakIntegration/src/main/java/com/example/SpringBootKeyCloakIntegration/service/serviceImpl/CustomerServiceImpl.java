package com.example.SpringBootKeyCloakIntegration.service.serviceImpl;

import com.example.SpringBootKeyCloakIntegration.model.Customer;
import com.example.SpringBootKeyCloakIntegration.repository.CustomerRepository;
import com.example.SpringBootKeyCloakIntegration.requestDTO.CustomerDTO;
import com.example.SpringBootKeyCloakIntegration.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveCustomerDetails(CustomerDTO customerDTO) throws Exception {

        if (Objects.nonNull(customerDTO.getEmail()) && Objects.nonNull(customerDTO.getPwd())){

            Customer customer = new Customer();

            List<Customer> customers = customerRepository.findByEmail(customerDTO.getEmail());
            if (customers.size()>0){
                throw new Exception("email already exists.!");
            }else {
                customer.setEmail(customerDTO.getEmail());
            }
            customer.setPwd(passwordEncoder.encode(customerDTO.getPwd()));
            customer.setRole(customerDTO.getRole());
            customerRepository.save(customer);

        }else throw new Exception("Bad Request.!");

        return "Customer Saved SuccessFully.";
    }

    @Override
    public List<Customer> getCustomerDetails() {
        return customerRepository.findAll();
    }


}
