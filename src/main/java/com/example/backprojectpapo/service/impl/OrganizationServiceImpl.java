package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.service.OrganizationService;

import java.util.List;
import java.util.Optional;

public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationRepository organizationRepository;

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public Optional<Organization> findById(Integer id) {
        return organizationRepository.findById(id);
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        organizationRepository.deleteById(id);
    }
}
