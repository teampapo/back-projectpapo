package com.example.backprojectpapo.repository;

import com.example.backprojectpapo.model.AggregatorSpecialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AggregatorSpecialistRepository extends JpaRepository<AggregatorSpecialist, Integer> {

    Optional<AggregatorSpecialist> findByEmail(String email);
}
