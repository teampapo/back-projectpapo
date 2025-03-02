package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.ConnectionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ConnectionRequestService {
    //TODO Filter
    //Page<ConnectionRequest> findAll(Specification<ConnectionRequest> spec, Pageable pageable);
    ConnectionRequest save(ConnectionRequest connectionRequest);
    Optional<ConnectionRequest> findById(Integer id);
    List<ConnectionRequest> findAll();
    Page<ConnectionRequest> findAll(Pageable pageable);
    void deleteById(int id);

}
