package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping()
    public ResponseEntity<OrganizationResponseDTO> getOrganization(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        OrganizationResponseDTO dto = organizationService.getOrganization(token.split(" ")[1]);
        return ResponseEntity.ok().body(dto);
    }
}
