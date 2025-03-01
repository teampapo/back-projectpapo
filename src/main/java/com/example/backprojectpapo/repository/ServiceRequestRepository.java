package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {
    //TODO @Nullable for Pageable ?
    Page<ServiceRequest> findAll(Pageable pageable);
}
