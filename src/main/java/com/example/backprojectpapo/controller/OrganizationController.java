package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.ServiceDetailOrganizationDTO;
import com.example.backprojectpapo.dto.request.OrganizationPostRequestDTO;
import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.service.OrganizationService;
import com.example.backprojectpapo.service.impl.ServiceDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final ServiceDetailServiceImpl serviceDetailService;

    @Autowired
    public OrganizationController(OrganizationService organizationService, ServiceDetailServiceImpl serviceDetailService) {
        this.organizationService = organizationService;
        this.serviceDetailService = serviceDetailService;
    }

    @GetMapping("/get_organization")
    public ResponseEntity<OrganizationResponseDTO> getOrganization(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        OrganizationResponseDTO dto = organizationService.getOrganization(token.split(" ")[1]);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/get_organization_services")
    public ResponseEntity<ResponseDto<ServiceDetailOrganizationDTO>> getOrganizationServices(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        ResponseDto<ServiceDetailOrganizationDTO> response = serviceDetailService.getOrganizationServices(token.split(" ")[1]);
        return ResponseEntity.ok().body(response);
    }

}
