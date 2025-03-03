package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.service.CustomerService;
import com.example.backprojectpapo.util.specification.CustomerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public ResponseDto<Customer> search(CustomerSearchCriteria criteria) {
        Specification<Customer> spec = CustomerSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new ResponseDto<>(customerRepository.findAll(spec,pageable));
    }

    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }


}
