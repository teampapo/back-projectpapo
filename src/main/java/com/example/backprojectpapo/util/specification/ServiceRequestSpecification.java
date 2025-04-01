package com.example.backprojectpapo.util.specification;

import com.example.backprojectpapo.dto.search.ServiceRequestSearchCriteria;
import com.example.backprojectpapo.model.ServiceRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequestSpecification {
    public static Specification<ServiceRequest> byCriteria(ServiceRequestSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getCustomerId() != null){
                predicates.add(criteriaBuilder.equal(root.get("customer").get("id"), criteria.getCustomerId()));
            }
            if (criteria.getOrganizationId() != null){
                predicates.add(criteriaBuilder.equal(root.get("organization").get("id"), criteria.getOrganizationId()));
            }
            if (criteria.getStartDate() != null){
                predicates.add(criteriaBuilder.equal(root.get("dateService"), criteria.getStartDate()));
            }
            if (criteria.getFromDateService() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateService"), criteria.getFromDateService()));
            }
            if (criteria.getToDateService() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateService"), criteria.getToDateService()));
            }

            predicates.addAll(BaseEntitySpecifications.byBaseCriteria(root,query,criteriaBuilder,criteria));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
