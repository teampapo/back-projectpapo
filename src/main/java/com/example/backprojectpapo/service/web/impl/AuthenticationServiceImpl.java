package com.example.backprojectpapo.service.web.impl;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.CustomerDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.exception.AuthenticationFailedException;
import com.example.backprojectpapo.exception.InvalidVerificationCodeException;
import com.example.backprojectpapo.exception.PasswordIsMissingException;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.service.UserService;
import com.example.backprojectpapo.service.email.EmailService;
import com.example.backprojectpapo.service.web.AuthenticationService;
import com.example.backprojectpapo.service.web.CustomUserDetailsService;
import com.example.backprojectpapo.service.web.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final EmailService emailService;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Временное хранилище кодов (email -> code)
    private final Map<String, String> verificationCodes = new HashMap<>();

    @Override
    public User signUpAggregatorSpecialist(AggregatorSpecialistDto dto) {

        return userService.registerAggregatorSpecialist(dto);
    }

    @Override
    public User signUpCustomer(CustomerDto dto) {

        return userService.registerCustomer(dto);
    }

    @Override
    public User signUpOrganization(OrganizationDto dto) {

        return userService.registerOrganization(dto);
    }

    @Override
    public String signInWithCode(String email, String verifyCode) {

        String storedCode = verificationCodes.get(email);
        if(storedCode == null || !storedCode.equals(verifyCode)){
            throw new InvalidVerificationCodeException("Invalid verification code");
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
        verificationCodes.remove(email);

        return jwtService.generateToken(customUserDetails);
    }

    @Override
    public String signInWithPasswordAndCode(String email, String password, String verifyCode) {

        String storedCode = verificationCodes.get(email);
        if(storedCode == null || !storedCode.equals(verifyCode)){
            throw new InvalidVerificationCodeException("Invalid verification code");
        }

        CustomUserDetails customUserDetails;
        try{
            String effectivePassword = Optional.ofNullable(password).orElse("");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, effectivePassword)
            );
            customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        }catch (AuthenticationException exception){
            throw new AuthenticationFailedException("Invalid email or password");
        }

        verificationCodes.remove(email);
        return jwtService.generateToken(customUserDetails);
    }

    @Override
    public void sendVerificationCode(String email) {

        String code = String.format("%06d", new Random().nextInt(999999));
        verificationCodes.put(email,code);

        //try {
        //    emailService.sendSimpleEmail(email, "verify code", "code: " + code);
        //}
        //catch (Exception e){
        //    System.out.println(code);
        //}
        System.out.println(code);

    }
}
