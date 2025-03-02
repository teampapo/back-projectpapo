package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceRequestService {
    //TODO Filter
    //Page<ConnectionRequest> findAll(Specification<ConnectionRequest> spec, Pageable pageable);
    ServiceRequest save(ServiceRequest serviceRequest);
    Optional<ServiceRequest> findById(Integer id);
    List<ServiceRequest> findAllList();
    Page<ServiceRequest> findAll(Pageable pageable);
    void deleteById(Integer id);
}
