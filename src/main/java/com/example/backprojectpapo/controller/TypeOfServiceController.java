package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.model.TypeOfService;
import com.example.backprojectpapo.service.TypeOfServiceService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public ResponseEntity<List<TypeOfService>> getTypeOfService() {
        return ResponseEntity.ok().body(typeOfServiceService.findAll());
    }
    @PostMapping()
    public ResponseEntity<TypeOfService> createTypeOfService(@RequestBody TypeOfService typeOfService) {
        return ResponseEntity.ok().body(typeOfServiceService.save(typeOfService));
    }
    @PutMapping()
    public ResponseEntity<TypeOfService> updateTypeOfService(@RequestBody TypeOfService typeOfService) {
        return ResponseEntity.ok().body(typeOfServiceService.save(typeOfService));
    }
    @DeleteMapping()
    public ResponseEntity<String> deleteTypeOfService(@RequestParam(name = "typeOfServiceId") Integer typeOfServiceId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
