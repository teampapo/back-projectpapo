package com.example.backprojectpapo.service;

import com.example.backprojectpapo.model.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

    Organization save(Organization organization);
    Optional<Organization> findById(Integer id);
    List<Organization> findAll();
    void deleteById(Integer id);

}
