package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.Address;
import com.example.backprojectpapo.repository.AddressRepository;
import com.example.backprojectpapo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);

    }

    @Override
    public Optional<Address> findById(Integer id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        addressRepository.deleteById(id);
    }
}
