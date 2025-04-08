package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.ConnectionRequestRequestDTO;
import com.example.backprojectpapo.dto.response.ConnectionRequestResponseDTO;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.exception.NotFoundException;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.enums.Status;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.repository.ConnectionRequestRepository;
import com.example.backprojectpapo.service.AggregatorSpecialistService;
import com.example.backprojectpapo.service.ConnectionRequestService;
import com.example.backprojectpapo.service.web.JwtService;
import com.example.backprojectpapo.util.specification.ConnectionRequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionRequestServiceImpl implements ConnectionRequestService {

    private final ConnectionRequestRepository connectionRequestRepository;
    private final JwtService jwtService;
    private final AggregatorSpecialistRepository aggregatorSpecialistRepository;

    @Autowired
    public ConnectionRequestServiceImpl(ConnectionRequestRepository connectionRequestRepository, JwtService jwtService, AggregatorSpecialistRepository aggregatorSpecialistRepository) {
        this.connectionRequestRepository = connectionRequestRepository;
        this.jwtService = jwtService;
        this.aggregatorSpecialistRepository = aggregatorSpecialistRepository;
    }

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
    public ResponseDto<ConnectionRequestResponseDTO> getAggregatorConnectionRequest(ConnectionRequestSearchCriteria criteria, String token) {
        Integer aggregatorId = jwtService.extractId(token);
        criteria.setAggregatorSpecialistId(aggregatorId);

        Specification<ConnectionRequest> spec = ConnectionRequestSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<ConnectionRequest> connectionRequest = connectionRequestRepository.findAll(spec, pageable);

        Page<ConnectionRequestResponseDTO> dtos = connectionRequest.map(ConnectionRequestResponseDTO::toDto);

        return new ResponseDto<>(dtos);
    }

    @Override
    public ResponseDto<ConnectionRequestResponseDTO> findByStatus(ConnectionRequestSearchCriteria criteria) {

        Specification<ConnectionRequest> spec = ConnectionRequestSpecification.byCriteria(criteria);
        Pageable pageable = PageRequest.of(criteria.getPage(), criteria.getSize());

        Page<ConnectionRequest> page = connectionRequestRepository.findAll(spec,pageable);
        Page<ConnectionRequestResponseDTO> pageDto = page.map(ConnectionRequestResponseDTO::toDto);

        return new ResponseDto<>(pageDto);
    }

    @Override
    public ArrayList<ConnectionRequest> findByOrganization(ConnectionRequestSearchCriteria criteria) {
        Specification<ConnectionRequest> spec = ConnectionRequestSpecification.byCriteria(criteria);

        return (ArrayList<ConnectionRequest>) connectionRequestRepository.findAll(spec);

    }

    @Override
    public Optional<ConnectionRequest> createConnectionRequest(Organization organization) {

        LocalDate currentDate = LocalDate.now();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        String regNumber = String.format("%s-%d", formattedDate, organization.getId());

        if (regNumber.length() > 20) {
            throw new IllegalArgumentException("Регистрационный номер превышает 20 символов");
        }

        ConnectionRequest connectionRequest = save(ConnectionRequest.builder()
                .organization(organization)
                .dateBegin(currentDate)
                .status(Status.NEW)
                .registrationNumber(regNumber)
                .build());

        return Optional.ofNullable(connectionRequest);
    }

    @Override
    public void updateConnectionRequestByAggregator(ConnectionRequestRequestDTO requestDTO, String token){
        Integer aggregatorId = jwtService.extractId(token);

        AggregatorSpecialist aggregatorSpecialist = aggregatorSpecialistRepository.findById(aggregatorId).orElseThrow(() -> new UserNotFoundException("Aggregator Specialist not found"));


        ConnectionRequest connectionRequest = connectionRequestRepository.findById(requestDTO.getId()).orElseThrow(() -> new UserNotFoundException("connectionRequest not found"));
        Optional.ofNullable(requestDTO.getStatus()).ifPresent(connectionRequest::setStatus);
        Optional.ofNullable(requestDTO.getAddInfo()).ifPresent(connectionRequest::setAddInfo);
        connectionRequest.getAggregatorSpecialists().add(aggregatorSpecialist);
        connectionRequestRepository.save(connectionRequest);

        aggregatorSpecialist.getConnectionRequests().add(connectionRequest);
        aggregatorSpecialistRepository.save(aggregatorSpecialist);
    }

    @Override
    public void deleteById(int id) {

        if (findById(id).isEmpty()) {
            throw new NotFoundException("this connection request not found");
        }
        connectionRequestRepository.deleteById(id);
    }
}
