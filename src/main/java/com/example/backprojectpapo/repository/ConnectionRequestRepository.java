package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.ServiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Integer> {
    //TODO @Nullable for Pageable ?
    Page<ConnectionRequest> findAll(Pageable pageable);
}
