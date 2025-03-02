package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Address save(Address address);
    Optional<Address> findById(Integer id);
    List<Address> findAll();
    void deleteById(Integer id);
}
