package com.example.backprojectpapo.controller;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.model.ConnectionRequest;
import com.example.backprojectpapo.model.enums.Status;
import com.example.backprojectpapo.service.ConnectionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/connection_request")
public class ConnectionRequestController {

    private final ConnectionRequestService connectionRequestService;

    @Autowired
    public ConnectionRequestController(ConnectionRequestService connectionRequestService) {
        this.connectionRequestService = connectionRequestService;
    }

    @GetMapping("/by_status")
    public ResponseEntity<ResponseDto<ConnectionRequest>> getByStatus(@RequestBody Status status,
                                                                                 @RequestBody ConnectionRequestSearchCriteria criteria) {
        criteria = criteria == null ? new ConnectionRequestSearchCriteria() : criteria;
        criteria.setStatus(status == null ? Status.NEW : status);

        ResponseDto<ConnectionRequest> connectionRequests = connectionRequestService.findByStatus(criteria);
        return ResponseEntity.status(HttpStatus.OK).body(connectionRequests);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Integer id){

        connectionRequestService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
