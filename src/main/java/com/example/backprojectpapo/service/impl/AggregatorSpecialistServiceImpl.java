package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.config.security.components.CustomUserDetails;
import com.example.backprojectpapo.dto.AggregatorSpecialistDTO;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.service.AggregatorSpecialistService;
import com.example.backprojectpapo.service.web.CustomUserDetailsService;
import com.example.backprojectpapo.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AggregatorSpecialistServiceImpl implements AggregatorSpecialistService {

    private final AggregatorSpecialistRepository aggregatorSpecialistRepository;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AggregatorSpecialistServiceImpl(AggregatorSpecialistRepository aggregatorSpecialistRepository, JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.aggregatorSpecialistRepository = aggregatorSpecialistRepository;
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public AggregatorSpecialist save(AggregatorSpecialist aggregatorSpecialist) {
        return aggregatorSpecialistRepository.save(aggregatorSpecialist);
    }

    @Override
    public AggregatorSpecialistDTO update(AggregatorSpecialistDTO dto, String token){
        Integer id = jwtService.extractId(token);
        AggregatorSpecialist aggregatorSpecialist = aggregatorSpecialistRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("AggregatorSpecialist not found"));

        boolean emailChanged = dto.getEmail() != null && !dto.getEmail().equals(aggregatorSpecialist.getEmail());

        Optional.ofNullable(aggregatorSpecialist.getEmail()).ifPresent(aggregatorSpecialist::setEmail);
        Optional.ofNullable(aggregatorSpecialist.getPassword()).ifPresent(aggregatorSpecialist::setPassword);
        Optional.ofNullable(dto.getSurname()).ifPresent(aggregatorSpecialist::setSurname);
        Optional.ofNullable(dto.getName()).ifPresent(aggregatorSpecialist::setName);
        Optional.ofNullable(dto.getPatronymic()).ifPresent(aggregatorSpecialist::setPatronymic);
        Optional.ofNullable(dto.getDepartment()).ifPresent(aggregatorSpecialist::setDepartment);
        Optional.ofNullable(dto.getPosition()).ifPresent(aggregatorSpecialist::setPosition);
        Optional.ofNullable(dto.getPhoneNumber()).ifPresent(aggregatorSpecialist::setPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(aggregatorSpecialist::setAddInfo);

        AggregatorSpecialist aggregatorSpecialist_ = aggregatorSpecialistRepository.save(aggregatorSpecialist);
        AggregatorSpecialistDTO aggregatorSpecialistDTO =  AggregatorSpecialistDTO.toDTO(aggregatorSpecialist_);

        String newToken = null;
        if(emailChanged){
            CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(aggregatorSpecialist_.getEmail());
            newToken = jwtService.generateToken(customUserDetails);
            aggregatorSpecialistDTO.setJwtToken(newToken);
        }

        return aggregatorSpecialistDTO;

    }


    @Override
    public AggregatorSpecialistDTO findByIdToDTO(String token) {

        Integer id = jwtService.extractId(token);

        AggregatorSpecialist aggregatorSpecialist = aggregatorSpecialistRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("AggregatorSpecialist not found"));

        return AggregatorSpecialistDTO.toDTO(aggregatorSpecialist);
    }

    @Override
    public Optional<AggregatorSpecialist> findById(Integer id){
        return aggregatorSpecialistRepository.findById(id);
    }

    @Override
    public List<AggregatorSpecialist> findAll() {
        return aggregatorSpecialistRepository.findAll();
    }

    @Override
    public void deleteById(String token) {
        Integer id = jwtService.extractId(token);
        aggregatorSpecialistRepository.deleteById(id);
    }

}
