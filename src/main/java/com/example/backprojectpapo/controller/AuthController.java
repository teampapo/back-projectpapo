package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.CustomerDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.dto.request.SignInWithCodeAndPasswordRequest;
import com.example.backprojectpapo.dto.request.SignInWithCodeRequest;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.service.web.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign_in/send_code")
    public ResponseEntity<?> sendCode(@RequestParam(name = "email") String email){
        authenticationService.sendVerificationCode(email);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/sign_up/customer")
    public ResponseEntity<String> signUpCustomer(@RequestBody CustomerDto customerDto){

        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpCustomer(customerDto));
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/sign_up/organization")
    public ResponseEntity<?> signUpOrganization(@RequestBody OrganizationDto organizationDto){

        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpOrganization(organizationDto));
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/sign_up/admin")
    public ResponseEntity<?> signUpAggregatorSpecialist(@RequestBody AggregatorSpecialistDto aggregatorSpecialistDto){

        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpAggregatorSpecialist(aggregatorSpecialistDto));
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PostMapping("/sign_in/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody SignInWithCodeRequest data){
        String token = authenticationService.signInWithCode(data.getEmail(), data.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


    @PostMapping("/sign_in/organization")
    public ResponseEntity<String> signInOrganization(@RequestBody SignInWithCodeRequest data){
        String token = authenticationService.signInWithCode(data.getEmail(), data.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/sign_in/admin")
    public ResponseEntity<String> signInAdmin(@RequestBody SignInWithCodeAndPasswordRequest data){
        String token = authenticationService.signInWithPasswordAndCode(data.getEmail(), data.getPassword(),
                data.getCode());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
