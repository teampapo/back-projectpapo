package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.ServiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Integer>, JpaSpecificationExecutor<ConnectionRequest> {
    //TODO @Nullable for Pageable ?
    //TODO Need page construction ( automatically ?)
    //Page<ConnectionRequest> findAll(Pageable pageable);
}
