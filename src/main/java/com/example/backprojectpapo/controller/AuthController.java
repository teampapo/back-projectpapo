package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.dto.TestDto;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.service.web.AuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signUp/customer")
    public ResponseEntity<String> signUpCustomer(@Valid @RequestBody TestDto testDto){

        System.out.println(testDto);
        System.out.println(testDto.getAboba());
        System.out.println(testDto.getSrulic());
        System.out.println(testDto.getLashka());
//        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpCustomer(customerDto));
//        if(optionalUser.isEmpty()){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
//        }
//
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
    public ResponseEntity<String> signInCustomer(@RequestBody String email, @RequestBody String code){
        String token = authenticationService.signInWithCode(email, code);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


    @PostMapping("/sign_in/organization")
    public ResponseEntity<String> signInOrganization(@RequestBody String email, @RequestBody String code){
        String token = authenticationService.signInWithCode(email, code);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/sign_in/admin")
    public ResponseEntity<String> signInAdmin(@RequestBody String email,@RequestBody String password,
                                              @RequestBody String code){
        String token = authenticationService.signInWithPasswordAndCode(email, password, code);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
