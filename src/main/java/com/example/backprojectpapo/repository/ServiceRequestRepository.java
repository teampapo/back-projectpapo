package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {
}
