package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.CustomerPutDTO;
import com.example.backprojectpapo.dto.request.PageParamsRequestDTO;
import com.example.backprojectpapo.dto.request.ServiceRequestCustomerCreateRequestDTO;
import com.example.backprojectpapo.dto.response.CustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestCustomerResponseDTO;
import com.example.backprojectpapo.dto.search.ServiceRequestSearchCriteria;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.model.jwt.JwtData;
import com.example.backprojectpapo.service.CustomerService;
import com.example.backprojectpapo.service.UserService;
import com.example.backprojectpapo.service.web.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtService jwtService;

    private JwtData extractJwtDataFromHeader(String authorizationHeader){

        String token = authorizationHeader.split(" ")[1];
        return jwtService.extractData(token);
    }

    @Autowired
    public CustomerController(CustomerService customerService, JwtService jwtService) {
        this.customerService = customerService;
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){

        JwtData jwtData = extractJwtDataFromHeader(authorizationHeader);
        Customer customer = customerService.findById(jwtData.getId()).get();
        return ResponseEntity.status(HttpStatus.OK).body(CustomerResponseDTO.toDto(customer));
    }

    @GetMapping("/service_type/organizations")
    public ResponseEntity<ResponseDto<OrganizationCustomerResponseDTO>> getOrganizationsByType(@RequestParam(name = "typeOfServiceId") Integer typeOfServiceId, PageParamsRequestDTO pageParamsRequestDTO){

        return ResponseEntity.status(HttpStatus.OK).body(customerService.responceDtoOrganizationsByTypeOfService(typeOfServiceId,pageParamsRequestDTO));
    }

    @PostMapping("/service_request/create")
    public ResponseEntity<ServiceRequestCustomerResponseDTO> addServicesToCustomer(@RequestBody @Valid ServiceRequestCustomerCreateRequestDTO requestDTO,@RequestHeader(HttpHeaders.AUTHORIZATION) String token){

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.setServiceRequestForCustomer(requestDTO,token.split(" ")[1]));
    }

    @PostMapping("/service_requests")
    public ResponseEntity<ResponseDto<ServiceRequestCustomerResponseDTO>> getServiceRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody(required = false) ServiceRequestSearchCriteria criteria){

        if(criteria == null){
            criteria = new ServiceRequestSearchCriteria();
        }
        JwtData jwtData = extractJwtDataFromHeader(authorizationHeader);
        Page<ServiceRequest> serviceRequests
                = customerService.findServiceRequestByCustomerIdAfterDatetime(jwtData.getId(), LocalDateTime.now(),
                criteria);

        Page<ServiceRequestCustomerResponseDTO> serviceRequestDTOS =
                serviceRequests.map(ServiceRequestCustomerResponseDTO::toDto);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(serviceRequestDTOS));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponseDTO> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @Valid @RequestBody CustomerPutDTO customerPutDTO){

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
    @DeleteMapping("/delete/request")
    public ResponseEntity<String> deleteServiceRequest(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,@RequestParam("serviceRequestId") Integer serviceRequestId){
        customerService.deleteServiceRequest(token.split(" ")[1],serviceRequestId);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
