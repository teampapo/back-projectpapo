package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
