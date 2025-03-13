package com.example.backprojectpapo.service.web;

import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.CustomerDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.model.user.User;

public interface AuthenticationService {
    User signUpAggregatorSpecialist(AggregatorSpecialistDto dto);
    User signUpCustomer(CustomerDto dto);
    User signUpOrganization(OrganizationDto dto);
    String signInWithCode(String email, String verifyCode);
    String signInWithPasswordAndCode(String email, String verifyCode, String password);
    void sendVerificationCode(String email);
}
