package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.request.AggregatorSpecialistDTO;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.service.AggregatorSpecialistService;
import com.example.backprojectpapo.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AggregatorSpecialistServiceImpl implements AggregatorSpecialistService {

    private final AggregatorSpecialistRepository aggregatorSpecialistRepository;
    private final JwtService jwtService;

    @Autowired
    public AggregatorSpecialistServiceImpl(AggregatorSpecialistRepository aggregatorSpecialistRepository, JwtService jwtService) {
        this.aggregatorSpecialistRepository = aggregatorSpecialistRepository;
        this.jwtService = jwtService;
    }

    @Override
    public AggregatorSpecialist save(AggregatorSpecialist aggregatorSpecialist) {
        return aggregatorSpecialistRepository.save(aggregatorSpecialist);
    }

    @Override
    public AggregatorSpecialist update(AggregatorSpecialistDTO dto, String token){
        Integer id = jwtService.extractId(token);
        AggregatorSpecialist aggregatorSpecialist = aggregatorSpecialistRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("AggregatorSpecialist not found"));

        Optional.ofNullable(dto.getSurname()).ifPresent(aggregatorSpecialist::setSurname);
        Optional.ofNullable(dto.getName()).ifPresent(aggregatorSpecialist::setName);
        Optional.ofNullable(dto.getPatronymic()).ifPresent(aggregatorSpecialist::setPatronymic);
        Optional.ofNullable(dto.getDepartment()).ifPresent(aggregatorSpecialist::setDepartment);
        Optional.ofNullable(dto.getPosition()).ifPresent(aggregatorSpecialist::setPosition);
        Optional.ofNullable(dto.getPhoneNumber()).ifPresent(aggregatorSpecialist::setPhoneNumber);
        Optional.ofNullable(dto.getAddInfo()).ifPresent(aggregatorSpecialist::setAddInfo);

        return aggregatorSpecialistRepository.save(aggregatorSpecialist);

    }


    @Override
    public AggregatorSpecialistDTO findByIdToDTO(String token) {

        Integer id = jwtService.extractId(token);

        AggregatorSpecialist aggregatorSpecialist = aggregatorSpecialistRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("AggregatorSpecialist not found"));

        return new AggregatorSpecialistDTO(
                aggregatorSpecialist.getSurname(),
                aggregatorSpecialist.getName(),
                aggregatorSpecialist.getPatronymic(),
                aggregatorSpecialist.getDepartment(),
                aggregatorSpecialist.getPosition(),
                aggregatorSpecialist.getPhoneNumber(),
                aggregatorSpecialist.getAddInfo()
        );
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
    public void deleteById(Integer id) {
        aggregatorSpecialistRepository.deleteById(id);
    }

}
