package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.AddressType;
import com.example.backprojectpapo.repository.AddressTypeRepository;
import com.example.backprojectpapo.service.AddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressTypeServiceImpl implements AddressTypeService {
    private final AddressTypeRepository addressTypeRepository;

    @Autowired
    public AddressTypeServiceImpl(AddressTypeRepository addressTypeRepository) {
        this.addressTypeRepository = addressTypeRepository;
    }

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
