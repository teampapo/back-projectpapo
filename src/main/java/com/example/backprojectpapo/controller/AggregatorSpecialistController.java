package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.AggregatorSpecialistDTO;
import com.example.backprojectpapo.dto.request.ConnectionRequestRequestDTO;
import com.example.backprojectpapo.dto.request.CustomerGetAggregatorDTO;

import com.example.backprojectpapo.dto.request.OrganizationGetAggregatorDTO;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.service.AggregatorSpecialistService;
import com.example.backprojectpapo.service.ConnectionRequestService;
import com.example.backprojectpapo.service.CustomerService;

import com.example.backprojectpapo.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aggreagator")
public class AggregatorSpecialistController {

    private final AggregatorSpecialistService aggregatorSpecialistService;
    private final CustomerService customerService;
    private final OrganizationService organizationService;
    private final ConnectionRequestService connectionRequestService;

    @Autowired
    public AggregatorSpecialistController(AggregatorSpecialistService aggregatorSpecialistService,CustomerService customerService,
                                          OrganizationService organizationService, ConnectionRequestService connectionRequestService) {
        this.aggregatorSpecialistService = aggregatorSpecialistService;
        this.customerService = customerService;
        this.organizationService = organizationService;
        this.connectionRequestService = connectionRequestService;

    }

    @GetMapping("/customers")
    public ResponseEntity<ResponseDto<CustomerGetAggregatorDTO>> getCustomerAggregator(@RequestBody(required = false) CustomerSearchCriteria criteria) {
        if (criteria == null) {
            criteria = new CustomerSearchCriteria();
        }
        ResponseDto<CustomerGetAggregatorDTO> responseDto = customerService.getCustomerForAggregator(criteria);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteCustomerAggregator(@RequestParam("customerId") Integer customerId) {
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/organization")
    public ResponseEntity<ResponseDto<OrganizationGetAggregatorDTO>> getOrganizationAggregator(@RequestBody(required = false) OrganizationSearchCriteria criteria) {
        if (criteria == null) {
            criteria = new OrganizationSearchCriteria();
        }
        ResponseDto<OrganizationGetAggregatorDTO> responseDto = organizationService.getOrganizationForAggregator(criteria);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/organization")
    public ResponseEntity<String> deleteOrganizationAggregator(@RequestParam(name = "organizationId") Integer organizationId) {
        organizationService.deleteById(organizationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/aggreagator")
    public ResponseEntity<AggregatorSpecialistDTO> getAggregatorSpecialist(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        AggregatorSpecialistDTO aggregatorSpecialist = aggregatorSpecialistService.findByIdToDTO(token.split(" ")[1]);

        return ResponseEntity.status(HttpStatus.OK).body(aggregatorSpecialist);
    }

    @PutMapping("/aggreagator")
    public ResponseEntity<AggregatorSpecialistDTO> updateAggregatorSpecialist(@Valid @RequestBody AggregatorSpecialistDTO aggregatorSpecialistDTO,
                                                             @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        AggregatorSpecialistDTO response = aggregatorSpecialistService.update(aggregatorSpecialistDTO, token.split(" ")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/aggreagator")
    public ResponseEntity<String> deleteAggregatorSpecialist(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        aggregatorSpecialistService.deleteById(token.split(" ")[1]);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/connectionRequest")
    public ResponseEntity<ResponseDto<ConnectionRequest>> searchConnectionRequests(@RequestBody(required = false) ConnectionRequestSearchCriteria criteria,
                                                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (criteria == null) {
            criteria = new ConnectionRequestSearchCriteria();
        }
        ResponseDto<ConnectionRequest> responseDto = connectionRequestService.search(criteria,token.split(" ")[1]);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/connectionRequest")
    public ResponseEntity<String> updateConnectionRequest(@RequestBody ConnectionRequestRequestDTO requestDTO) {
        connectionRequestService.updateConnectionRequestByAggregator(requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
