package com.example.backprojectpapo.service.web;

import com.example.backprojectpapo.dto.request.AuthAggregatorSpecialistRequestDTO;
import com.example.backprojectpapo.dto.request.AuthCustomerRequestDTO;
import com.example.backprojectpapo.dto.request.AuthOrganizationRequestDTO;
import com.example.backprojectpapo.model.user.User;

public interface AuthenticationService {
    User signUpAggregatorSpecialist(AuthAggregatorSpecialistRequestDTO dto);
    User signUpCustomer(AuthCustomerRequestDTO dto);
    User signUpOrganization(AuthOrganizationRequestDTO dto);
    Boolean checkIsValidCodeByEmail(String email, String verifyCode);
    String signInWithCode(String email, String verifyCode);
    String signInWithPasswordAndCode(String email, String verifyCode, String password);
    void sendVerificationCode(String email);
}
