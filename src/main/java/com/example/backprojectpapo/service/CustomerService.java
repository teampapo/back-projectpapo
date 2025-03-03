package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    //TODO Filter
    //TODO do we need Page<Customer> findAll(Pageable pageable);
    Customer save(Customer customer);
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
    Page<Customer> findAll(Pageable pageable);
    ResponseDto<Customer> search(CustomerSearchCriteria criteria);
    void deleteById(Integer id);

}
