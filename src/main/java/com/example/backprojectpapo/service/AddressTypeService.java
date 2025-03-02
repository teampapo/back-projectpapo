package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.AddressType;

import java.util.List;
import java.util.Optional;

public interface AddressTypeService {

    AddressType save(AddressType addressType);
    Optional<AddressType> findById(Integer id);
    List<AddressType> findAll();
    void deleteById(int id);
}
