package com.example.backprojectpapo.service.impl;

import com.example.backprojectpapo.dto.request.TypeOfServiceRequestDTO;
import com.example.backprojectpapo.dto.response.TypeOfServiceResponseDTO;
import com.example.backprojectpapo.exception.NotFoundException;
import com.example.backprojectpapo.exception.OrganizationConnectionRequestNotApprovedException;
import com.example.backprojectpapo.exception.UserAlreadyExistsException;
import com.example.backprojectpapo.exception.UserNotFoundException;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.Organization;
import com.example.backprojectpapo.model.TypeOfService;
import com.example.backprojectpapo.model.enums.Role;
import com.example.backprojectpapo.model.enums.Status;
import com.example.backprojectpapo.model.jwt.JwtData;
import com.example.backprojectpapo.repository.OrganizationRepository;
import com.example.backprojectpapo.repository.TypeOfServiceRepository;
import com.example.backprojectpapo.service.TypeOfServiceService;
import com.example.backprojectpapo.service.web.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeOfServiceServiceImpl implements TypeOfServiceService {
    private final TypeOfServiceRepository typeOfServiceRepository;
    private final OrganizationRepository organizationRepository;
    private  final JwtService jwtService;

    @Autowired
    public TypeOfServiceServiceImpl(TypeOfServiceRepository typeOfServiceRepository, OrganizationRepository organizationRepository, JwtService jwtService) {
        this.typeOfServiceRepository = typeOfServiceRepository;
        this.organizationRepository = organizationRepository;
        this.jwtService = jwtService;
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
    public List<TypeOfServiceResponseDTO> findAll(String token) {
        JwtData jwtData = jwtService.extractData(token);
        if(jwtData.getRole().equals(Role.ORGANIZATION)){
            // проверка статуса заявки на подключение у организации, если не выполнена, то исключение
            Organization organization = organizationRepository.findById(jwtData.getId()).orElseThrow(() -> new NotFoundException("Organization not found"));
            List<ConnectionRequest> connReqOrganization=organization.getConnectionRequests().stream().toList();
            if(!connReqOrganization.get(connReqOrganization.size()-1).getStatus().equals(Status.COMPLETED)){
                throw new OrganizationConnectionRequestNotApprovedException("The organization's connection request was " +
                        "not approved");
            }
            //
        }

        List<TypeOfService> typeOfService = typeOfServiceRepository.findAll();
        List<TypeOfServiceResponseDTO> responseDTOS = typeOfService.stream().map(TypeOfServiceResponseDTO::toDTO).toList();
        return responseDTOS;
    }

    @Override
    public void deleteById(Integer id) {
        typeOfServiceRepository.deleteById(id);
    }


}
