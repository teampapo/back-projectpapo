package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.model.ServiceDetail;
import com.example.backprojectpapo.service.ServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service_detail")
public class ServiceDetailController {
    private final ServiceDetailService serviceDetailService;

    @Autowired
    public ServiceDetailController(ServiceDetailService serviceDetailService) {
        this.serviceDetailService = serviceDetailService;
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<ServiceDetail>> getServiceDetail(@RequestBody(required = false) ServiceDetailSearchCriteria criteria) {
        if (criteria == null) {
            criteria = new ServiceDetailSearchCriteria();
        }
        ResponseDto<ServiceDetail> responseDto = serviceDetailService.search(criteria);
        return ResponseEntity.ok().body(responseDto);
    }
}
