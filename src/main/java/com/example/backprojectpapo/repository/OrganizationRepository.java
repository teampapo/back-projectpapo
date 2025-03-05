package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer>, JpaSpecificationExecutor<Organization> {
    Optional<Organization> findByResponsiblePersonEmail(String responsiblePersonEmail);
    Optional<Organization> findByEmail(String email);
}
