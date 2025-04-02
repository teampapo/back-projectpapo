package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.CustomerGetAggregatorDTO;
import com.example.backprojectpapo.dto.request.CustomerPutDTO;
import com.example.backprojectpapo.dto.request.PageParamsRequestDTO;
import com.example.backprojectpapo.dto.request.ServiceRequestCustomerCreateRequestDTO;
import com.example.backprojectpapo.dto.response.CustomerResponseDTO;
import com.example.backprojectpapo.dto.response.OrganizationCustomerResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceRequestCustomerResponseDTO;
import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.dto.search.ServiceRequestSearchCriteria;
import com.example.backprojectpapo.exception.InvalidRequestException;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.repository.CustomerRepository;
import com.example.backprojectpapo.repository.ServiceRequestRepository;
import com.example.backprojectpapo.service.CustomerService;
import com.example.backprojectpapo.service.web.CustomUserDetailsService;
import com.example.backprojectpapo.service.web.JwtService;
import com.example.backprojectpapo.util.specification.CustomerSpecification;
import com.example.backprojectpapo.util.specification.ServiceRequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    private final ServiceRequestRepository serviceRequestRepository;
    private final OrganizationServiceImpl organizationService;
    private final ServiceRequestServiceImpl serviceRequestService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomUserDetailsService customUserDetailsService, JwtService jwtService, ServiceRequestRepository serviceRequestRepository, OrganizationServiceImpl organizationService, ServiceRequestServiceImpl serviceRequestService) {
        this.customerRepository = customerRepository;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = jwtService;
        this.serviceRequestRepository = serviceRequestRepository;
        this.organizationService = organizationService;
        this.serviceRequestService = serviceRequestService;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer id) {

        Optional<Customer> customer = customerRepository.findById(id);
        return Optional.of(
                customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Customer not found"))
        );
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public ResponseDto<Customer> search(CustomerSearchCriteria criteria) {
        Specification<Customer> spec = CustomerSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new ResponseDto<>(customerRepository.findAll(spec, pageable));
    }

    @Override
    public Page<ServiceRequest> findServiceRequestByCustomerIdAfterDatetime(Integer customerId,
                                                                            LocalDateTime dateTime,
                                                                            ServiceRequestSearchCriteria criteria) {

        criteria.setFromDateService(dateTime);
        criteria.setCustomerId(customerId);
        Specification<ServiceRequest> spec = ServiceRequestSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        //return customerRepository.findServiceRequestByCustomerIdAfterDatetime(customerId, dateTime, pageable);
        return serviceRequestRepository.findAll(spec, pageable);
    }

    @Override
    public CustomerResponseDTO update(Integer id, CustomerPutDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null");
        }

        Customer customer = findById(id)
                .orElseThrow(() -> new UserNotFoundException("This user not found"));

        boolean emailChanged = dto.getEmail() != null && !dto.getEmail().equals(customer.getEmail());

        Optional.ofNullable(dto.getEmail()).ifPresent(customer::setEmail);
        Optional.ofNullable(dto.getSurname()).ifPresent(customer::setSurname);
        Optional.ofNullable(dto.getName()).ifPresent(customer::setName);
        Optional.ofNullable(dto.getPatronymic()).ifPresent(customer::setPatronymic);
        Optional.ofNullable(dto.getPhoneNumber()).ifPresent(customer::setPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(customer::setAddInfo);

        Customer customer_ = customerRepository.save(customer);
        CustomerResponseDTO customerResponseDTO = CustomerResponseDTO.toDto(customer_);

        String newToken = null;
        if(emailChanged){
            CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(customer_.getEmail());
            newToken = jwtService.generateToken(customUserDetails);
            customerResponseDTO.setJwtToken(newToken);
        }

        return customerResponseDTO;
    }

    @Override
    public ResponseDto<CustomerGetAggregatorDTO> getCustomerForAggregator(CustomerSearchCriteria criteria) {
        Specification<Customer> spec = CustomerSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<Customer> customers = customerRepository.findAll(spec,pageable);
        Page<CustomerGetAggregatorDTO> dtoPage = customers.map(this::convertToDto);
        return new ResponseDto<>(dtoPage);
        
    }

    private CustomerGetAggregatorDTO convertToDto(Customer customer) {
        return new CustomerGetAggregatorDTO(
                customer.getId(),
                customer.getSurname(),
                customer.getName(),
                customer.getPatronymic(),
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }

    @Override
    public ResponseDto<OrganizationCustomerResponseDTO> responceDtoOrganizationsByTypeOfService(Integer serviceTypeId, PageParamsRequestDTO pageParamsRequestDTO){
        return organizationService.getOrganizationsByServiceType(serviceTypeId,pageParamsRequestDTO);
    }
    @Override
    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public ServiceRequestCustomerResponseDTO setServiceRequestForCustomer(ServiceRequestCustomerCreateRequestDTO requestDTO,String token){
        return serviceRequestService.save(requestDTO,token);
    }
    @Override
    public void deleteServiceRequest(String token, Integer serviceRequestId){
        Integer customerId = jwtService.extractId(token);
        ServiceRequest serviceRequest = serviceRequestRepository.findById(serviceRequestId).orElseThrow(() -> new UserNotFoundException("This service request not found"));
        if (!customerId.equals(serviceRequest.getCustomer().getId())) {
            throw new InvalidRequestException("This service request does not belong to the customer " + customerId);
        }
        serviceRequestRepository.delete(serviceRequest);
    }
}
