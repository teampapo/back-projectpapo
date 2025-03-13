package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.CustomerDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findUserByEmail(String email);
    User registerAggregatorSpecialist(AggregatorSpecialistDto user);
    User registerCustomer(CustomerDto user);
    User registerOrganization(OrganizationDto user);
}

