package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.dto.AddressDTO;
import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.OrganizationGetAggregatorDTO;
import com.example.backprojectpapo.dto.request.OrganizationPostRequestDTO;
import com.example.backprojectpapo.dto.request.PageParamsRequestDTO;
import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestOrganizationResponseDTO;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.exception.InvalidRequestException;
import com.example.backprojectpapo.exception.NotFoundException;
import com.example.backprojectpapo.exception.OrganizationConnectionRequestNotApprovedException;
import com.example.backprojectpapo.model.Address;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.model.enums.Status;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.service.AddressService;
import com.example.backprojectpapo.service.ConnectionRequestService;
import com.example.backprojectpapo.service.OrganizationService;
import com.example.backprojectpapo.service.ServiceRequestService;
import com.example.backprojectpapo.service.web.CustomUserDetailsService;
import com.example.backprojectpapo.service.web.JwtService;
import com.example.backprojectpapo.util.specification.OrganizationSpecification;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Builder
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final JwtService jwtService;
    private final ConnectionRequestService connectionRequestService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ServiceRequestService serviceRequestService;
    private final AddressService addressService;


    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, JwtService jwtService, ConnectionRequestService connectionRequestService, CustomUserDetailsService customUserDetailsService, ServiceRequestService serviceRequestService, AddressService addressService) {
        this.organizationRepository = organizationRepository;
        this.jwtService = jwtService;
        this.connectionRequestService = connectionRequestService;

        this.customUserDetailsService = customUserDetailsService;
        this.serviceRequestService = serviceRequestService;
        this.addressService = addressService;
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

        List<ConnectionRequest> connectionRequest = organization.getConnectionRequests().stream().toList();
        Status status = connectionRequest.get(connectionRequest.size()-1).getStatus();
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
                organization.getResponsiblePersonPhoneNumber(),
                status
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
        organizationResponseDTO.setConnectionRequestStatus(connectionRequest.get(connectionRequest.size()-1).getStatus());
        return organizationResponseDTO;
    }

    @Override
    public OrganizationResponseDTO updateOrganization(OrganizationPostRequestDTO dto, String token){
        Integer id = jwtService.extractId(token);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Organization not found"));

        // проверка статуса заявки на подключение у организации, если не выполнена, то исключение
        List<ConnectionRequest> connReqOrganization=organization.getConnectionRequests().stream().toList();
        if(!connReqOrganization.get(connReqOrganization.size()-1).getStatus().equals(Status.COMPLETED)){
            throw new OrganizationConnectionRequestNotApprovedException("The organization's connection request was " +
                    "not approved");
        }
        //

        List<Address> addressOrganization = organization.getAddresses().stream().toList();
        Integer addressOrganizationId = addressOrganization.get(0).getId();

        List<AddressDTO> addressDto_ = dto.getAddresses().stream().toList();
        Integer addressDtoId = addressDto_.get(0).getId();

        if (dto.getAddresses().size() > 1 || !addressOrganizationId.equals(addressDtoId)) {
            throw new InvalidRequestException("Invalid address");
        }

        boolean emailChanged = (dto.getEmail() != null && !dto.getEmail().equals(organization.getEmail())) || (dto.getResponsiblePersonEmail() != null && !dto.getResponsiblePersonEmail().equals(organization.getResponsiblePersonEmail()));

        Optional.ofNullable(dto.getFullName()).ifPresent(organization::setFullName);
        Optional.ofNullable(dto.getShortName()).ifPresent(organization::setShortName);
        Optional.ofNullable(dto.getInn()).ifPresent(organization::setInn);
        Optional.ofNullable(dto.getKpp()).ifPresent(organization::setKpp);
        Optional.ofNullable(dto.getOgrn()).ifPresent(organization::setOgrn);
        Optional.ofNullable(dto.getResponsiblePersonSurname()).ifPresent(organization::setResponsiblePersonSurname);
        Optional.ofNullable(dto.getResponsiblePersonName()).ifPresent(organization::setResponsiblePersonName);
        Optional.ofNullable(dto.getResponsiblePersonPatronymic()).ifPresent(organization::setResponsiblePersonPatronymic);

        Optional.ofNullable(dto.getResponsiblePersonEmail()).ifPresent(organization::setResponsiblePersonEmail);
        Optional.ofNullable(dto.getResponsiblePersonEmail()).ifPresent(organization::setEmail);

        Optional.ofNullable(dto.getResponsiblePersonPhoneNumber()).ifPresent(organization::setResponsiblePersonPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(organization::setAddInfo);

        Optional.ofNullable(dto.getEmail()).ifPresent(organization::setEmail);
        Optional.ofNullable(dto.getEmail()).ifPresent(organization::setResponsiblePersonEmail);

        // Обновление адресов
        Optional.ofNullable(dto.getAddresses()).ifPresent(newAddresses -> {

            Set<Address> updatedAddresses = newAddresses.stream().map(addressDto -> {

                if (addressDto.getId() == null) {
                    throw new InvalidRequestException("Address id requried");
                }

                Address address = addressService.findById(addressDto.getId()).orElseThrow(() -> new NotFoundException("Address not found"));

                address.setOrganization(organization);
                Optional.ofNullable(addressDto.getSubjectName()).ifPresent(address::setSubjectName);
                Optional.ofNullable(address.getCityName()).ifPresent(address::setCityName);
                Optional.ofNullable(addressDto.getStreetName()).ifPresent(address::setStreetName);
                Optional.ofNullable(addressDto.getHouseNumber()).ifPresent(address::setHouseNumber);
                Optional.ofNullable(addressDto.getAddInfo()).ifPresent(address::setAddInfo);


                return address;
            }).collect(Collectors.toSet());

            organization.setAddresses(updatedAddresses);
        });

        Organization newOrganization = organizationRepository.save(organization);
        OrganizationResponseDTO organizationResponseDTO = OrganizationResponseDTO.toDto(newOrganization);

        String newToken = null;
        if(emailChanged){
            CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(newOrganization.getEmail());
            newToken = jwtService.generateToken(customUserDetails);
            organizationResponseDTO.setJwtToken(newToken);
        }

        return organizationResponseDTO;
    }

    @Override
    public ResponseDto<ServiceRequestOrganizationResponseDTO> getServiceRequestOrganization(String token,PageParamsRequestDTO pageParamsRequestDTO){
        Integer id = jwtService.extractId(token);
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Organization not found"));

        // проверка статуса заявки на подключение у организации, если не выполнена, то исключение
        List<ConnectionRequest> connReqOrganization=organization.getConnectionRequests().stream().toList();
        if(!connReqOrganization.get(connReqOrganization.size()-1).getStatus().equals(Status.COMPLETED)){
            throw new OrganizationConnectionRequestNotApprovedException("The organization's connection request was " +
                    "not approved");
        }
        //

        Page<ServiceRequest> serviceRequestsPage = serviceRequestService.getServiceRequestOrganizationByOrganizationId(id,pageParamsRequestDTO);
        Page<ServiceRequestOrganizationResponseDTO> dtoPage = serviceRequestsPage.map(ServiceRequestOrganizationResponseDTO::toDTO);

        return new ResponseDto<>(dtoPage);
    }

    @Override
    public ResponseDto<OrganizationCustomerResponseDTO> getOrganizationsByServiceType(Integer serviceTypeId, PageParamsRequestDTO pageParamsRequestDTO){

        OrganizationSearchCriteria criteria = new OrganizationSearchCriteria();
        criteria.setTypeOfServiceId(serviceTypeId);
        criteria.setDistinct(true);

        Optional.ofNullable(pageParamsRequestDTO.getPage()).ifPresent(criteria::setPage);
        Optional.ofNullable(pageParamsRequestDTO.getSize()).ifPresent(criteria::setSize);

        Specification<Organization> spec = OrganizationSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<Organization> organization_ = organizationRepository.findAll(spec,pageable);
        Page<OrganizationCustomerResponseDTO> dtoPage = organization_.map(OrganizationCustomerResponseDTO::toDto);

        return new ResponseDto<>(dtoPage);
    }

    @Override
    public void deleteById(Integer id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NotFoundException("Organization not found"));

        // проверка статуса заявки на подключение у организации, если не выполнена, то исключение
        List<ConnectionRequest> connReqOrganization=organization.getConnectionRequests().stream().toList();
        if(!connReqOrganization.get(connReqOrganization.size()-1).getStatus().equals(Status.COMPLETED)){
            throw new OrganizationConnectionRequestNotApprovedException("The organization's connection request was " +
                    "not approved");
        }
        //

        organizationRepository.deleteById(id);
    }
}
