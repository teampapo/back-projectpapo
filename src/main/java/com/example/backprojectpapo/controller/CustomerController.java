package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.request.CustomerPutDTO;
import com.example.backprojectpapo.dto.response.CustomerResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestCustomerResponseDTO;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.model.jwt.JwtData;
import com.example.backprojectpapo.service.CustomerService;
import com.example.backprojectpapo.service.UserService;
import com.example.backprojectpapo.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final UserService userService;
    private final CustomerService customerService;
    private final JwtService jwtService;

    private JwtData extractJwtDataFromHeader(String authorizationHeader){

        String token = authorizationHeader.split(" ")[1];
        return jwtService.extractData(token);
    }

    @Autowired
    public CustomerController(UserService userService, CustomerService customerService, JwtService jwtService) {
        this.userService = userService;
        this.customerService = customerService;
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){

        JwtData jwtData = extractJwtDataFromHeader(authorizationHeader);
        Customer customer = customerService.findById(jwtData.getId()).get();
        return ResponseEntity.status(HttpStatus.OK).body(CustomerResponseDTO.toDto(customer));
    }

    @GetMapping("/service_requests")
    public ResponseEntity<List<ServiceRequestCustomerResponseDTO>> getServiceRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){

        JwtData jwtData = extractJwtDataFromHeader(authorizationHeader);
        List<ServiceRequest> serviceRequests = customerService.findServiceRequestByCustomerIdAfterDatetime(jwtData.getId(),
                LocalDateTime.now());

        List<ServiceRequestCustomerResponseDTO> serviceRequestDTOS = serviceRequests
                .stream().map(ServiceRequestCustomerResponseDTO::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(serviceRequestDTOS);
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponseDTO> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,@RequestBody CustomerPutDTO customerPutDTO){

        JwtData jwtData = extractJwtDataFromHeader(authorizationHeader);
        CustomerResponseDTO customerResponseDTO = customerService.update(jwtData.getId(), customerPutDTO);

        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){

        JwtData jwtData = extractJwtDataFromHeader(authorizationHeader);
        customerService.deleteById(jwtData.getId());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
