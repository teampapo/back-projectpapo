package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.repository.ServiceDetailRepository;
import com.example.backprojectpapo.service.ServiceDetailService;
import com.example.backprojectpapo.util.specification.ServiceDetailSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
    public ResponseDto<ServiceDetail> search(ServiceDetailSearchCriteria criteria) {
        Specification<ServiceDetail> spec = ServiceDetailSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());
        return new ResponseDto<>(serviceDetailRepository.findAll(spec,pageable));
    }

    @Override
    public void deleteById(Integer id) {
        serviceDetailRepository.deleteById(id);
    }
}
