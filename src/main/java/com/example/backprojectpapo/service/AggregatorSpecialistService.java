package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.AggregatorSpecialist;

import java.util.List;
import java.util.Optional;

public interface AggregatorSpecialistService {

    AggregatorSpecialist save(AggregatorSpecialist aggregatorSpecialist);
    Optional<AggregatorSpecialist> findById(Integer id);
    List<AggregatorSpecialist> findAll();
    void deleteById(Integer id);
}
