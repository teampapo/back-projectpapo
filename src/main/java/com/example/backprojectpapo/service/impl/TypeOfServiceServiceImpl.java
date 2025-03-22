package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.request.TypeOfServiceRequestDTO;
import com.example.backprojectpapo.dto.response.TypeOfServiceResponseDTO;
import com.example.backprojectpapo.exception.UserAlreadyExistsException;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.TypeOfService;
import com.example.backprojectpapo.repository.TypeOfServiceRepository;
import com.example.backprojectpapo.service.TypeOfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeOfServiceServiceImpl implements TypeOfServiceService {
    private final TypeOfServiceRepository typeOfServiceRepository;

    @Autowired
    public TypeOfServiceServiceImpl(TypeOfServiceRepository typeOfServiceRepository) {
        this.typeOfServiceRepository = typeOfServiceRepository;
    }

    @Override
    public TypeOfServiceResponseDTO save(TypeOfServiceRequestDTO requestDTO) {
        TypeOfService typeOfService = new TypeOfService();
        if (typeOfServiceRepository.findByCodeAndName(requestDTO.getCode(), requestDTO.getName()).isPresent()) {
            throw new UserAlreadyExistsException("This type of service already exists");
        }

        typeOfService.setCode(requestDTO.getCode());
        typeOfService.setName(requestDTO.getName());
        TypeOfService typeOfService_ = typeOfServiceRepository.save(typeOfService);

        return TypeOfServiceResponseDTO.toDTO(typeOfService_);
    }

    @Override
    public TypeOfServiceResponseDTO update(TypeOfServiceRequestDTO requestDTO){
        TypeOfService typeOfService = typeOfServiceRepository.findById(requestDTO.getId()).orElseThrow(()-> new UserNotFoundException("This type of service does not exist"));
        Optional.ofNullable(requestDTO.getName()).ifPresent(typeOfService::setName);
        Optional.ofNullable(requestDTO.getCode()).ifPresent(typeOfService::setCode);
        return TypeOfServiceResponseDTO.toDTO(typeOfServiceRepository.save(typeOfService));
    }

    @Override
    public Optional<TypeOfService> findById(Integer id) {
        return typeOfServiceRepository.findById(id);
    }

    @Override
    public List<TypeOfServiceResponseDTO> findAll() {
        List<TypeOfService> typeOfService = typeOfServiceRepository.findAll();
        List<TypeOfServiceResponseDTO> responseDTOS = typeOfService.stream().map(TypeOfServiceResponseDTO::toDTO).toList();
        return responseDTOS;
    }

    @Override
    public void deleteById(Integer id) {
        typeOfServiceRepository.deleteById(id);
    }


}
