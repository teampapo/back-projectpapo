package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.request.AuthAggregatorSpecialistRequestDTO;
import com.example.backprojectpapo.dto.request.AuthCustomerRequestDTO;
import com.example.backprojectpapo.dto.request.AuthOrganizationRequestDTO;
import com.example.backprojectpapo.model.user.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findUserByEmail(String email);
    User registerAggregatorSpecialist(AuthAggregatorSpecialistRequestDTO user);
    User registerCustomer(AuthCustomerRequestDTO user);
    User registerOrganization(AuthOrganizationRequestDTO user);
}

