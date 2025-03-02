package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.repository.ConnectionRequestRepository;
import com.example.backprojectpapo.service.ConnectionRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ConnectionRequestServiceImpl implements ConnectionRequestService {

    private ConnectionRequestRepository connectionRequestRepository;

    @Override
    public ConnectionRequest save(ConnectionRequest connectionRequest) {
        return connectionRequestRepository.save(connectionRequest);
    }

    @Override
    public Optional<ConnectionRequest> findById(Integer id) {
        return connectionRequestRepository.findById(id);
    }

    @Override
    public List<ConnectionRequest> findAll() {
        return connectionRequestRepository.findAll();
    }

    @Override
    public Page<ConnectionRequest> findAll(Pageable pageable) {
        return connectionRequestRepository.findAll(pageable);
    }

    @Override
    public void deleteById(int id) {
        connectionRequestRepository.deleteById(id);
    }
}
