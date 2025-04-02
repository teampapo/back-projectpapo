package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.ServiceDetailOrganizationDTO;
import com.example.backprojectpapo.dto.request.ServiceDetailPostRequestDTO;
import com.example.backprojectpapo.dto.request.ServiceDetailPutRequestDTO;
import com.example.backprojectpapo.dto.response.ServiceDetailResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceDetailWithOrganizationAllResponseDTO;
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
    ServiceDetailResponseDTO save(ServiceDetailPostRequestDTO postRequestDTO, String token);

    ServiceDetailResponseDTO update(ServiceDetailPutRequestDTO dto,String token);

    Optional<ServiceDetail> findById(Integer id);
    List<ServiceDetail> findAllList();
    Page<ServiceDetail> findAll(Pageable pageable);
    ResponseDto<ServiceDetail> search(ServiceDetailSearchCriteria criteria);
    ResponseDto<ServiceDetailOrganizationDTO> getOrganizationServices(String token);
    ResponseDto<ServiceDetailWithOrganizationAllResponseDTO> getAllServiceDetailByCriteria(ServiceDetailSearchCriteria criteria,String token);

    void deleteById(Integer id,String token);
}
