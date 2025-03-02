package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }


}
