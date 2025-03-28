package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.ConnectionRequestRequestDTO;
import com.example.backprojectpapo.dto.response.ConnectionRequestResponseDTO;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ConnectionRequestService {
    //TODO Filter
    //Page<ConnectionRequest> findAll(Specification<ConnectionRequest> spec, Pageable pageable);
    //TODO do we need Page<ConnectionRequest> findAll(Pageable pageable);
    ConnectionRequest save(ConnectionRequest connectionRequest);
    Optional<ConnectionRequest> findById(Integer id);
    List<ConnectionRequest> findAll();
    Page<ConnectionRequest> findAll(Pageable pageable);
    ResponseDto<ConnectionRequestResponseDTO> search(ConnectionRequestSearchCriteria criteria, String token);
    ResponseDto<ConnectionRequestResponseDTO> findByStatus(ConnectionRequestSearchCriteria criteria);


    ArrayList<ConnectionRequest> findByOrganization(ConnectionRequestSearchCriteria criteria);
    Optional<ConnectionRequest> createConnectionRequest(Organization organization);

    void updateConnectionRequestByAggregator(ConnectionRequestRequestDTO requestDTO,String token);

    void deleteById(int id);

}
