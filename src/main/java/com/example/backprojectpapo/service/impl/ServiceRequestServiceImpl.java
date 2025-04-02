package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.PageParamsRequestDTO;
import com.example.backprojectpapo.dto.request.ServiceRequestCustomerCreateRequestDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestCustomerResponseDTO;
import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.dto.search.ServiceRequestSearchCriteria;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.repository.ServiceDetailRepository;
import com.example.backprojectpapo.repository.ServiceRequestRepository;
import com.example.backprojectpapo.service.ServiceRequestService;
import com.example.backprojectpapo.service.web.JwtService;
import com.example.backprojectpapo.util.specification.ServiceDetailSpecification;
import com.example.backprojectpapo.util.specification.ServiceRequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
    private final ServiceRequestRepository serviceRequestRepository;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;
    private final OrganizationRepository organizationRepository;
    private final ServiceDetailRepository serviceDetailRepository;


    @Autowired
    public ServiceRequestServiceImpl(ServiceRequestRepository serviceRequestRepository, JwtService jwtService, CustomerRepository customerRepository, OrganizationRepository organizationRepository, ServiceDetailRepository serviceDetailRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
        this.organizationRepository = organizationRepository;
        this.serviceDetailRepository = serviceDetailRepository;
    }

    @Override
    public ServiceRequestCustomerResponseDTO save(ServiceRequestCustomerCreateRequestDTO requestDTO, String token) {
        Integer customerId = jwtService.extractId(token);

        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new UserNotFoundException("Customer not found for ID: " + customerId));
        Organization organization = organizationRepository.findById(requestDTO.getOrganizationId()).orElseThrow(()-> new UserNotFoundException("Organization not found for ID: " + requestDTO.getOrganizationId()));

        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setCustomer(customer);
        serviceRequest.setOrganization(organization);
        serviceRequest.setDateService(requestDTO.getDateService());
        serviceRequest.setAddInfo(requestDTO.getAddInfo());

        Set<ServiceDetail> serviceDetails = new HashSet<>();
        for (Integer serviceDetailId : requestDTO.getServiceDetailIds()) {
            ServiceDetailSearchCriteria criteria = new ServiceDetailSearchCriteria();
            criteria.setId(serviceDetailId);
            criteria.setOrganizationId(requestDTO.getOrganizationId());

            Specification<ServiceDetail> spec = ServiceDetailSpecification.byCriteria(criteria);


            List<ServiceDetail> foundServiceDetails  = serviceDetailRepository.findAll(spec);

            if (foundServiceDetails.isEmpty()) {
                throw new UserNotFoundException("ServiceDetail with ID: "+ serviceDetailId+" for Organization ID: "+ requestDTO.getOrganizationId()+" not found");
            }

            serviceDetails.addAll(foundServiceDetails);
        }

        serviceRequest.setServiceDetails(serviceDetails);

        serviceRequest = serviceRequestRepository.save(serviceRequest);

        return ServiceRequestCustomerResponseDTO.toDto(serviceRequest);
    }

    @Override
    public Optional<ServiceRequest> findById(Integer id) {
        return serviceRequestRepository.findById(id);
    }

    @Override
    public List<ServiceRequest> findAllList() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public Page<ServiceRequest> findAll(Pageable pageable) {
        return serviceRequestRepository.findAll(pageable);
    }

    @Override
    public ResponseDto<ServiceRequest> search(ServiceRequestSearchCriteria criteria) {
        Specification<ServiceRequest> spec = ServiceRequestSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new ResponseDto<>(serviceRequestRepository.findAll(spec,pageable));
    }

    @Override
    public Page<ServiceRequest> getServiceRequestOrganizationByOrganizationId(Integer id, PageParamsRequestDTO pageParamsRequestDTO){
        ServiceRequestSearchCriteria criteria = new  ServiceRequestSearchCriteria();
        criteria.setOrganizationId(id);

        Optional.ofNullable(pageParamsRequestDTO.getPage()).ifPresent(criteria::setPage);
        Optional.ofNullable(pageParamsRequestDTO.getSize()).ifPresent(criteria::setSize);

        Specification<ServiceRequest> spec = ServiceRequestSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        Page<ServiceRequest> page = serviceRequestRepository.findAll(spec,pageable);

        return page;
    }

    @Override
    public void deleteById(Integer id) {
        serviceRequestRepository.deleteById(id);
    }


}
