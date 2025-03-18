package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.CustomerGetAggregatorDTO;
import com.example.backprojectpapo.dto.request.CustomerPutDTO;
import com.example.backprojectpapo.dto.response.CustomerResponseDTO;
import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.model.Customer;
import com.example.backprojectpapo.model.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    //TODO Filter
    //TODO do we need Page<Customer> findAll(Pageable pageable);
    Customer save(Customer customer);
    Optional<Customer> findById(Integer id);
    List<Customer> findAll();
    Page<Customer> findAll(Pageable pageable);
    ResponseDto<Customer> search(CustomerSearchCriteria criteria);
    ResponseDto<CustomerGetAggregatorDTO> getCustomerForAggregator(CustomerSearchCriteria criteria);
    List<ServiceRequest> findServiceRequestByCustomerIdAfterDatetime(Integer customerId, LocalDateTime dateTime);
    CustomerResponseDTO update(Integer id, CustomerPutDTO dto);
    void deleteById(Integer id);
}
