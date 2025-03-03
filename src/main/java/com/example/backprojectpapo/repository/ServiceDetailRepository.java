package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.ServiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceDetailRepository extends JpaRepository<ServiceDetail, Integer>, JpaSpecificationExecutor<ServiceDetail> {
    //TODO @Nullable for Pageable ?
    //Page<ServiceDetail> findAll(Pageable pageable);
}
