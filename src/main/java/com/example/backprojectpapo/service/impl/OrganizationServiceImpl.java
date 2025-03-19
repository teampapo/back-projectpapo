package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.OrganizationGetAggregatorDTO;
import com.example.backprojectpapo.dto.request.OrganizationPostRequestDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.exception.NotFoundException;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.service.ConnectionRequestService;
import com.example.backprojectpapo.service.OrganizationService;
import com.example.backprojectpapo.service.web.JwtService;
import com.example.backprojectpapo.util.specification.OrganizationSpecification;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final JwtService jwtService;
    private final ConnectionRequestService connectionRequestService;


    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, JwtService jwtService, ConnectionRequestService connectionRequestService) {
        this.organizationRepository = organizationRepository;
        this.jwtService = jwtService;
        this.connectionRequestService = connectionRequestService;

    }

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> findById(Integer id) {
        return organizationRepository.findById(id);
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Page<Organization> findAll(Pageable pageable) {
        return organizationRepository.findAll(pageable);
    }

    @Override
    public ResponseDto<Organization> search(OrganizationSearchCriteria criteria) {
        Specification<Organization> spec = OrganizationSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new ResponseDto<>(organizationRepository.findAll(spec,pageable));
    }

    @Override
    public ResponseDto<OrganizationGetAggregatorDTO> getOrganizationForAggregator(OrganizationSearchCriteria criteria){
        Specification<Organization> spec = OrganizationSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<Organization> organizations = organizationRepository.findAll(spec,pageable);
        Page<OrganizationGetAggregatorDTO> dtoPage = organizations.map(this::convertToDto);
        return new ResponseDto<>(dtoPage);
    }

    private OrganizationGetAggregatorDTO convertToDto(Organization organization) {
        return new OrganizationGetAggregatorDTO(
                organization.getId(),
                organization.getFullName(),
                organization.getShortName(),
                organization.getInn(),
                organization.getKpp(),
                organization.getOgrn(),
                organization.getResponsiblePersonSurname(),
                organization.getResponsiblePersonName(),
                organization.getResponsiblePersonPatronymic(),
                organization.getResponsiblePersonEmail(),
                organization.getResponsiblePersonPhoneNumber()
        );
    }

    @Override
    public OrganizationResponseDTO getOrganization(String token){
        Integer id = jwtService.extractId(token);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Organization not found"));

        OrganizationResponseDTO organizationResponseDTO =  OrganizationResponseDTO.toDto(organization);
        ConnectionRequestSearchCriteria criteria = new ConnectionRequestSearchCriteria();
        criteria.setOrganizationId(id);
        //criteria.setSortBy("dateBegin");
        ArrayList<ConnectionRequest> connectionRequest = connectionRequestService.findByOrganization(criteria);
        organizationResponseDTO.setStatus(connectionRequest.get(connectionRequest.size()-1).getStatus());
        return organizationResponseDTO;
    }


    @Override
    public void deleteById(Integer id) {
        organizationRepository.deleteById(id);
    }
}
