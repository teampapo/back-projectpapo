package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.AggregatorSpecialistDTO;
import com.example.backprojectpapo.dto.request.CustomerGetAggregatorDTO;

import com.example.backprojectpapo.dto.request.OrganizationGetAggregatorDTO;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.service.AggregatorSpecialistService;
import com.example.backprojectpapo.service.ConnectionRequestService;
import com.example.backprojectpapo.service.CustomerService;

import com.example.backprojectpapo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/agregator")
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
    public ResponseEntity<ResponseDto<CustomerGetAggregatorDTO>> getCustomerAggregator(@RequestBody CustomerSearchCriteria criteria) {
        ResponseDto<CustomerGetAggregatorDTO> responseDto = customerService.getCustomerForAggregator(criteria);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/customers")
    public ResponseEntity<String> deleteCustomerAggregator(@RequestBody Integer customerId) {
        customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/organization")
    public ResponseEntity<ResponseDto<OrganizationGetAggregatorDTO>> getOrganizationAggregator(@RequestBody OrganizationSearchCriteria criteria) {
        ResponseDto<OrganizationGetAggregatorDTO> responseDto = organizationService.getOrganizationForAggregator(criteria);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/organization")
    public ResponseEntity<String> deleteOrganizationAggregator(@RequestBody Integer organizationId) {
        organizationService.deleteById(organizationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //TODO жду security для получения и декомпозции JWT токена ( получить id )
    @GetMapping("/aggreagator")
    public ResponseEntity<AggregatorSpecialistDTO> getAggregatorSpecialist(@RequestBody Integer aggregatorId) {
        Optional<AggregatorSpecialistDTO> aggregatorSpecialist = aggregatorSpecialistService.findByIdToDTO(aggregatorId);
        assert aggregatorSpecialist.isPresent(); //TODO заменить assert

        return ResponseEntity.status(HttpStatus.OK).body(aggregatorSpecialist.get());
    }

    //TODO жду security для получения и декомпозции JWT токена ( получить id )
    @PutMapping("/aggreagator")
    public ResponseEntity<String> updateAggregatorSpecialist(@RequestBody AggregatorSpecialistDTO aggregatorSpecialistDTO,
                                                             @RequestParam Integer aggregatorId) {

        Optional<AggregatorSpecialist> optionalSpecialist = aggregatorSpecialistService.findById(aggregatorId);
        if (optionalSpecialist.isEmpty()) {
            return new ResponseEntity<>("AggregatorSpecialist not found", HttpStatus.NOT_FOUND);
        }
        aggregatorSpecialistService.save(aggregatorSpecialistDTO.toAggregatorSpecialist(optionalSpecialist.get()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //TODO жду security для получения и декомпозции JWT токена ( получить id )
    @GetMapping("/connectionRequest")
    public ResponseEntity<ResponseDto<ConnectionRequest>> searchConnectionRequests(@RequestBody ConnectionRequestSearchCriteria criteria,
                                                                                   @RequestBody Integer aggregatorId) {
        ResponseDto<ConnectionRequest> responseDto = connectionRequestService.search(criteria,aggregatorId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PutMapping("/connectionRequest")
    public ResponseEntity<String> updateConnectionRequest(@RequestBody ConnectionRequest connectionRequest) {
        connectionRequestService.save(connectionRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
