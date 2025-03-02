package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.repository.ServiceRequestRepository;
import com.example.backprojectpapo.service.ServiceRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ServiceRequestServiceImpl implements ServiceRequestService {
    private ServiceRequestRepository serviceRequestRepository;
    @Override
    public ServiceRequest save(ServiceRequest serviceRequest) {
        return serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public Optional<ServiceRequest> findById(Integer id) {
        return serviceRequestRepository.findById(id);
    }

    @Override
    public List<ServiceRequest> findAllList() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public Page<ServiceRequest> findAll(Pageable pageable) {
        return serviceRequestRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        serviceRequestRepository.deleteById(id);
    }


}
