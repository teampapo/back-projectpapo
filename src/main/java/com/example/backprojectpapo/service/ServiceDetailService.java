package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.ServiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceDetailService {
    //TODO Filter
    //Page<ConnectionRequest> findAll(Specification<ConnectionRequest> spec, Pageable pageable);
    ServiceDetail save(ServiceDetail serviceDetail);
    Optional<ServiceDetail> findById(Integer id);
    List<ServiceDetail> findAllList();
    Page<ServiceDetail> findAll(Pageable pageable);
    void deleteById(Integer id);
}
