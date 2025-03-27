package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.TypeOfService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeOfServiceRepository extends JpaRepository<TypeOfService, Integer> {
    Optional<TypeOfService>  findByCodeAndName(String code, String name);
}
