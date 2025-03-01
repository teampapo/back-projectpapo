package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.repository.ServiceDetailRepository;
import com.example.backprojectpapo.service.ServiceDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class ServiceDetailServiceImpl implements ServiceDetailService {
    private ServiceDetailRepository serviceDetailRepository;
    @Override
    public ServiceDetail save(ServiceDetail serviceDetail) {
        return serviceDetailRepository.save(serviceDetail);
    }

    @Override
    public Optional<ServiceDetail> findById(Integer id) {
        return serviceDetailRepository.findById(id);
    }

    @Override
    public List<ServiceDetail> findAllList() {
        return serviceDetailRepository.findAll();
    }

    @Override
    public Page<ServiceDetail> findAll(Pageable pageable) {
        return serviceDetailRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Integer id) {
        serviceDetailRepository.deleteById(id);
    }
}
