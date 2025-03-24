package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.request.ServiceDetailPutRequestDTO;
import com.example.backprojectpapo.dto.response.ServiceDetailResponseDTO;
import com.example.backprojectpapo.dto.response.ServiceDetailWithOrganizationAllResponseDTO;
import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service_detail")
public class ServiceDetailController {
    private final ServiceDetailService serviceDetailService;

    @Autowired
    public ServiceDetailController(ServiceDetailService serviceDetailService) {
        this.serviceDetailService = serviceDetailService;
    }

    @GetMapping("/get_all_services")
    public ResponseEntity<ResponseDto<ServiceDetailWithOrganizationAllResponseDTO>> getServiceDetailByCriteria(@RequestBody(required = false) ServiceDetailSearchCriteria criteria) {
        if (criteria == null) {
            criteria = new ServiceDetailSearchCriteria();
        }
        ResponseDto<ServiceDetailWithOrganizationAllResponseDTO> responseDto = serviceDetailService.getAllServiceDetailByCriteria(criteria);
        return ResponseEntity.ok().body(responseDto);
    }
    @PostMapping()
    public ResponseEntity<ServiceDetailResponseDTO> createService(@RequestBody ServiceDetail serviceDetail){
        ServiceDetailResponseDTO responseDTO = serviceDetailService.save(serviceDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping()
    public ResponseEntity<ServiceDetailResponseDTO> updateService(@RequestBody ServiceDetailPutRequestDTO dto){
        return ResponseEntity.ok().body(serviceDetailService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteService(@RequestParam(name = "serviceId") Integer serviceId){
        serviceDetailService.deleteById(serviceId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
