package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.response.OrganizationGetAggregatorDTO;
import com.example.backprojectpapo.dto.request.OrganizationPostRequestDTO;
import com.example.backprojectpapo.dto.request.PageParamsRequestDTO;
import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestOrganizationResponseDTO;
import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    //TODO filter
    //TODO do we need Page<Organization> findAll(Pageable pageable);
    Organization save(Organization organization);
    Optional<Organization> findById(Integer id);
    List<Organization> findAll();
    Page<Organization> findAll(Pageable pageable);
    ResponseDto<Organization> search(OrganizationSearchCriteria criteria);

    ResponseDto<OrganizationGetAggregatorDTO> getOrganizationForAggregator(OrganizationSearchCriteria criteria);

    OrganizationResponseDTO getOrganization(String token);

    OrganizationResponseDTO updateOrganization(OrganizationPostRequestDTO dto, String token);

    ResponseDto<ServiceRequestOrganizationResponseDTO> getServiceRequestOrganization(String token,PageParamsRequestDTO pageParamsRequestDTO);

    ResponseDto<OrganizationCustomerResponseDTO> getOrganizationsByServiceType(Integer serviceTypeId,String typeOfServiceCode, PageParamsRequestDTO pageParamsRequestDTO);

    void deleteById(Integer id);

}
