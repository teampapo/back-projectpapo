package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.Address;
import com.example.backprojectpapo.repository.AddressRepository;
import com.example.backprojectpapo.service.AddressService;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

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
