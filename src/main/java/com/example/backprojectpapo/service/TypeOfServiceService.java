package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.request.TypeOfServiceRequestDTO;
import com.example.backprojectpapo.dto.response.TypeOfServiceResponseDTO;
import com.example.backprojectpapo.model.TypeOfService;

import java.util.List;
import java.util.Optional;

public interface TypeOfServiceService {

    TypeOfServiceResponseDTO save(TypeOfServiceRequestDTO typeOfService);

    TypeOfServiceResponseDTO update(TypeOfServiceRequestDTO requestDTO);

    Optional<TypeOfService> findById(Integer id);
    List<TypeOfServiceResponseDTO> findAll();
    void deleteById(Integer id);
}
