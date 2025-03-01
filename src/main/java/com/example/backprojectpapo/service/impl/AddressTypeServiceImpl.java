package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.AddressType;
import com.example.backprojectpapo.repository.AddressTypeRepository;
import com.example.backprojectpapo.service.AddressTypeService;

import java.util.List;
import java.util.Optional;

public class AddressTypeServiceImpl implements AddressTypeService {
    private AddressTypeRepository addressTypeRepository;
    @Override
    public AddressType save(AddressType addressType) {
        return addressTypeRepository.save(addressType);
    }

    @Override
    public Optional<AddressType> findById(Integer id) {
        return addressTypeRepository.findById(id);
    }

    @Override
    public List<AddressType> findAll() {
        return addressTypeRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        addressTypeRepository.deleteById(id);
    }
}
