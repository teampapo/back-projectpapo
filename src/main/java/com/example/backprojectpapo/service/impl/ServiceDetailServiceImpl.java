package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.ServiceDetailOrganizationDTO;
import com.example.backprojectpapo.dto.request.ServiceDetailPutRequestDTO;
import com.example.backprojectpapo.dto.response.ServiceDetailResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceDetailWithOrganizationAllResponseDTO;
import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.exception.InvalidRequestException;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.repository.ServiceDetailRepository;
import com.example.backprojectpapo.service.ServiceDetailService;
import com.example.backprojectpapo.service.web.JwtService;
import com.example.backprojectpapo.util.specification.ServiceDetailSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceDetailServiceImpl implements ServiceDetailService {
    private final ServiceDetailRepository serviceDetailRepository;
    private final JwtService jwtService;
    @Autowired
    public ServiceDetailServiceImpl(ServiceDetailRepository serviceDetailRepository, JwtService jwtService) {
        this.serviceDetailRepository = serviceDetailRepository;
        this.jwtService = jwtService;
    }

    @Override
    public ServiceDetailResponseDTO save(ServiceDetail serviceDetail) {

        if (serviceDetail.getOrganization() == null || serviceDetail.getOrganization().getId() == null) {
            throw new InvalidRequestException("Organization ID is required");
        }
        if (serviceDetail.getType() == null || serviceDetail.getType().getId() == null) {
            throw new InvalidRequestException("Type ID is required");
        }

        ServiceDetail serviceDetail_ =  serviceDetailRepository.save(serviceDetail);
        return ServiceDetailResponseDTO.toDto(serviceDetail_);
    }

    @Override
    public ServiceDetailResponseDTO update(ServiceDetailPutRequestDTO dto){
        ServiceDetail serviceDetail = serviceDetailRepository.findById(dto.getId()).orElseThrow(()-> new UserNotFoundException("Service not found"));

        Optional.ofNullable(dto.getCode()).ifPresent(serviceDetail::setCode);
        Optional.ofNullable(dto.getName()).ifPresent(serviceDetail::setName);
        Optional.ofNullable(dto.getCost()).ifPresent(serviceDetail::setCost);
        Optional.ofNullable(dto.getDuration()).ifPresent(serviceDetail::setDuration);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(serviceDetail::setAddInfo);

        ServiceDetail newServiceDetail =  serviceDetailRepository.save(serviceDetail);

        return ServiceDetailResponseDTO.toDto(newServiceDetail);
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
    public ResponseDto<ServiceDetailOrganizationDTO> getOrganizationServices(String token) {
        Integer id = jwtService.extractId(token);
        ServiceDetailSearchCriteria criteria = new ServiceDetailSearchCriteria();
        criteria.setOrganizationId(id);

        Specification<ServiceDetail> spec = ServiceDetailSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<ServiceDetail> serviceDetails = serviceDetailRepository.findAll(spec, pageable);

        Page<ServiceDetailOrganizationDTO> serviceDetailOrganizationDTOS = serviceDetails.map(ServiceDetailOrganizationDTO::toDto);

        return new ResponseDto<>(serviceDetailOrganizationDTOS);
    }

    @Override
    public ResponseDto<ServiceDetailWithOrganizationAllResponseDTO> getAllServiceDetailByCriteria(ServiceDetailSearchCriteria criteria){
        Specification<ServiceDetail> spec = ServiceDetailSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<ServiceDetail> serviceDetail = serviceDetailRepository.findAll(spec,pageable);

        Page<ServiceDetailWithOrganizationAllResponseDTO> serviceDetailResponseDTOs = serviceDetail.map(ServiceDetailWithOrganizationAllResponseDTO::toDTO);
        return new ResponseDto<>(serviceDetailResponseDTOs);
    }

    @Override
    public void deleteById(Integer id) {
        serviceDetailRepository.deleteById(id);
    }
}
