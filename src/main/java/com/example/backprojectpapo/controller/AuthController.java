package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.AggregatorSpecialistDto;
import com.example.backprojectpapo.dto.CustomerDto;
import com.example.backprojectpapo.dto.OrganizationDto;
import com.example.backprojectpapo.model.user.User;
import com.example.backprojectpapo.service.web.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Tag(name = "Аутентификация", description = "API для регистрации и входа пользователей")

public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Отправка кода подтверждения", description = "Отправляет код подтверждения на email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Код отправлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка запроса")
    })
    @PostMapping("/sign_in/send_code")
    public ResponseEntity<?> sendCode(@RequestParam(name = "email") String email){
        authenticationService.sendVerificationCode(email);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @Operation(summary = "Регистрация клиента", description = "Создаёт новый аккаунт клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент зарегистрирован"),
            @ApiResponse(responseCode = "500", description = "Ошибка создания пользователя")
    })
    @PostMapping("/sign_up/customer")
    public ResponseEntity<String> signUpCustomer(@RequestBody CustomerDto customerDto){

        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpCustomer(customerDto));
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @Operation(summary = "Регистрация организации", description = "Создаёт новый аккаунт организации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Организация зарегистрирована"),
            @ApiResponse(responseCode = "500", description = "Ошибка создания организации")
    })
    @PostMapping("/sign_up/organization")
    public ResponseEntity<?> signUpOrganization(@RequestBody OrganizationDto organizationDto){

        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpOrganization(organizationDto));
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @Operation(summary = "Регистрация администратора", description = "Создаёт нового администратора (специалиста агрегатора)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Администратор зарегистрирована"),
            @ApiResponse(responseCode = "500", description = "Ошибка создания администратора")
    })
    @PostMapping("/sign_up/admin")
    public ResponseEntity<?> signUpAggregatorSpecialist(@RequestBody AggregatorSpecialistDto aggregatorSpecialistDto){

        Optional<User> optionalUser = Optional.ofNullable(authenticationService.signUpAggregatorSpecialist(aggregatorSpecialistDto));
        if(optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not create");
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }


    @Operation(summary = "Вход клиента", description = "Авторизация клиента по коду")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная авторизация",
                    content = @Content(schema = @Schema(example = "jwt_token"))),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации")
    })
    @PostMapping("/sign_in/customer")
    public ResponseEntity<String> signInCustomer(@RequestBody Map<String,String> data){
        String token = authenticationService.signInWithCode(data.get("email"), data.get("code"));
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Operation(summary = "Вход организации", description = "Авторизация организации по коду")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная авторизация",
                    content = @Content(schema = @Schema(example = "jwt_token"))),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации")
    })
    @PostMapping("/sign_in/organization")
    public ResponseEntity<String> signInOrganization(@RequestBody Map<String,String> data){
        String token = authenticationService.signInWithCode(data.get("email"), data.get("code"));
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @Operation(summary = "Вход администратора", description = "Авторизация администратора по паролю и коду")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная авторизация",
                    content = @Content(schema = @Schema(example = "jwt_token"))),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации")
    })
    @PostMapping("/sign_in/admin")
    public ResponseEntity<String> signInAdmin(@RequestBody Map<String, String> data){
        String token = authenticationService.signInWithPasswordAndCode(data.get("email"), data.get("password"),
                data.get("code"));
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
