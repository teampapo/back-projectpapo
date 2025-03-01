package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.TypeOfService;

import java.util.List;
import java.util.Optional;

public interface TypeOfServiceService {

    TypeOfService save(TypeOfService typeOfService);
    Optional<TypeOfService> findById(Integer id);
    List<TypeOfService> findAll();
    void deleteById(Integer id);
}
