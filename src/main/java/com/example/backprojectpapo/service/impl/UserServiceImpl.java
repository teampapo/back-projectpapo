package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final AggregatorSpecialistRepository aggregatorSpecialistRepository;
    private final CustomerRepository customerRepository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public UserServiceImpl(AggregatorSpecialistRepository aggregatorSpecialistRepository,
                       CustomerRepository customerRepository,
                       OrganizationRepository organizationRepository) {
        this.aggregatorSpecialistRepository = aggregatorSpecialistRepository;
        this.customerRepository = customerRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<AggregatorSpecialist> aggregatorSpecialist= aggregatorSpecialistRepository.findByEmail(email);
        if (aggregatorSpecialist.isPresent()) {
            return Optional.of(aggregatorSpecialist.get());
        }

        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            return Optional.of(customer.get());
        }

        Optional<Organization> organization = organizationRepository.findByEmail(email);
        if (organization.isPresent()) {
            return Optional.of(organization.get());
        }

        throw new RuntimeException("User with email " + email + " not found");
    }
}
