package com.example.backprojectpapo.service;

import com.example.backprojectpapo.dto.ResponseDto;
import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {
    //TODO filter
    //TODO do we need Page<Organization> findAll(Pageable pageable);
    Organization save(Organization organization);
    Optional<Organization> findById(Integer id);
    List<Organization> findAll();
    Page<Organization> findAll(Pageable pageable);
    ResponseDto<Organization> search(OrganizationSearchCriteria criteria);
    void deleteById(Integer id);

}
