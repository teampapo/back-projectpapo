package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.request.TypeOfServiceRequestDTO;
import com.example.backprojectpapo.dto.response.TypeOfServiceResponseDTO;
import com.example.backprojectpapo.model.TypeOfService;
import com.example.backprojectpapo.service.TypeOfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type_of_service")
public class TypeOfServiceController {

    private final TypeOfServiceService typeOfServiceService;

    @Autowired
    public TypeOfServiceController(TypeOfServiceService typeOfServiceService) {
        this.typeOfServiceService = typeOfServiceService;
    }


    @GetMapping("/get")
    public ResponseEntity<List<TypeOfServiceResponseDTO>> getTypeOfService(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok().body(typeOfServiceService.findAll(token.split(" ")[1]));
    }
    @PostMapping()
    public ResponseEntity<TypeOfServiceResponseDTO> createTypeOfService(@RequestBody TypeOfServiceRequestDTO requestDTO) {
        return ResponseEntity.ok().body(typeOfServiceService.save(requestDTO));
    }
    @PutMapping()
    public ResponseEntity<TypeOfServiceResponseDTO> updateTypeOfService(@RequestBody TypeOfServiceRequestDTO requestDTO) {
        return ResponseEntity.ok().body(typeOfServiceService.update(requestDTO));
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteTypeOfService(@RequestParam(name = "typeOfServiceId") Integer typeOfServiceId) {
        typeOfServiceService.deleteById(typeOfServiceId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
