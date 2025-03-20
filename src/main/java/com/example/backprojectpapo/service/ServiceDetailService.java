package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.response.ServiceDetailResponseDTO;
import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.model.ServiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServiceDetailService {
    //TODO Filter
    //Page<ConnectionRequest> findAll(Specification<ConnectionRequest> spec, Pageable pageable);
    //TODO do we need Page<ServiceDetail> findAll(Pageable pageable);??
    ServiceDetail save(ServiceDetail serviceDetail);
    Optional<ServiceDetail> findById(Integer id);
    List<ServiceDetail> findAllList();
    Page<ServiceDetail> findAll(Pageable pageable);
    ResponseDto<ServiceDetail> search(ServiceDetailSearchCriteria criteria);

    ResponseDto<ServiceDetailResponseDTO> getAllServiceDetailByCriteria(ServiceDetailSearchCriteria criteria);

    void deleteById(Integer id);
}
