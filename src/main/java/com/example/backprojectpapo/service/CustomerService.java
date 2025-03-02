package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
    void deleteById(Integer id);

}
