package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.ServiceDetailOrganizationDTO;
import com.example.backprojectpapo.dto.request.OrganizationPostRequestDTO;
import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestOrganizationResponseDTO;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.service.OrganizationService;
import com.example.backprojectpapo.service.impl.ServiceDetailServiceImpl;
import com.example.backprojectpapo.service.web.JwtService;
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
    private final JwtService jwtService;

    @Autowired
    public OrganizationController(OrganizationService organizationService, ServiceDetailServiceImpl serviceDetailService, JwtService jwtService) {
        this.organizationService = organizationService;
        this.serviceDetailService = serviceDetailService;
        this.jwtService = jwtService;
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

    @PutMapping("/update/organization")
    public ResponseEntity<OrganizationResponseDTO> updateOrganization(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody OrganizationPostRequestDTO organizationPostRequestDTO) {
        OrganizationResponseDTO responseDTO = organizationService.updateOrganization(organizationPostRequestDTO,token.split(" ")[1]);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/get_organization_services_requests")
    public ResponseEntity<ResponseDto<ServiceRequestOrganizationResponseDTO>> getOrganizationServiceRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        ResponseDto<ServiceRequestOrganizationResponseDTO> responseDto = organizationService.getServiceRequestOrganization(token.split(" ")[1]);
        return ResponseEntity.ok().body(responseDto);
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteOrganization(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Integer id = jwtService.extractId(token.split(" ")[1]);
        organizationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
