package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.repository.AggregatorSpecialistRepository;
import com.example.backprojectpapo.service.AggregatorSpecialistService;

import java.util.List;
import java.util.Optional;

public class AggregatorSpecialistServiceImpl implements AggregatorSpecialistService {

    private AggregatorSpecialistRepository aggregatorSpecialistRepository;

    @Override
    public AggregatorSpecialist save(AggregatorSpecialist aggregatorSpecialist) {
        return aggregatorSpecialistRepository.save(aggregatorSpecialist);
    }

    @Override
    public Optional<AggregatorSpecialist> findById(Integer id) {
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
