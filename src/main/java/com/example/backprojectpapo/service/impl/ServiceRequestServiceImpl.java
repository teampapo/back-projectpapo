package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.ServiceRequestSearchCriteria;
import com.example.backprojectpapo.model.ServiceRequest;
import com.example.backprojectpapo.repository.ServiceRequestRepository;
import com.example.backprojectpapo.service.ServiceRequestService;
import com.example.backprojectpapo.util.specification.ServiceRequestSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
    public ResponseDto<ServiceRequest> search(ServiceRequestSearchCriteria criteria) {
        Specification<ServiceRequest> spec = ServiceRequestSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new ResponseDto<>(serviceRequestRepository.findAll(spec,pageable));
    }

    @Override
    public void deleteById(Integer id) {
        serviceRequestRepository.deleteById(id);
    }


}
