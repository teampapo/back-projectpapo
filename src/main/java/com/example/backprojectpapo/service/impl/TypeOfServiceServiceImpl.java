package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.TypeOfService;
import com.example.backprojectpapo.repository.TypeOfServiceRepository;
import com.example.backprojectpapo.service.TypeOfServiceService;

import java.util.List;
import java.util.Optional;

public class TypeOfServiceServiceImpl implements TypeOfServiceService {
    private TypeOfServiceRepository typeOfServiceRepository;

    @Override
    public TypeOfService save(TypeOfService typeOfService) {
        return typeOfServiceRepository.save(typeOfService);
    }

    @Override
    public Optional<TypeOfService> findById(Integer id) {
        return typeOfServiceRepository.findById(id);
    }

    @Override
    public List<TypeOfService> findAll() {
        return typeOfServiceRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        typeOfServiceRepository.deleteById(id);
    }


}
