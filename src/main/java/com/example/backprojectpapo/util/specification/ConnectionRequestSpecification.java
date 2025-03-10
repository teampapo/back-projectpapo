package com.example.backprojectpapo.util.specification;

import com.example.backprojectpapo.dto.search.ConnectionRequestSearchCriteria;
import com.example.backprojectpapo.model.AggregatorSpecialist;
import com.example.backprojectpapo.model.ConnectionRequest;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ConnectionRequestSpecification {
    public static Specification<ConnectionRequest> byCriteria(ConnectionRequestSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getOrganizationId() != null){
                predicates.add(criteriaBuilder.equal(root.get("organization").get("id"), criteria.getOrganizationId()));
            }
            if (criteria.getRegistrationNumber() != null && !criteria.getRegistrationNumber().isEmpty()){
                predicates.add(criteriaBuilder.like(root.get("registrationNumber"), "%"+criteria.getRegistrationNumber()+"%"));
            }
            if (criteria.getFromDateBegin() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateBegin"), criteria.getFromDateBegin()));
            }
            if (criteria.getToDateBegin() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateBegin"), criteria.getToDateBegin()));
            }
            if (criteria.getFromDateEnd() != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateEnd"), criteria.getFromDateEnd()));
            }
            if (criteria.getToDateEnd() != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dateEnd"), criteria.getToDateEnd()));
            }
            if (criteria.getStatus() != null){
                predicates.add(criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
            }
            if (criteria.getAggregatorSpecialistId() != null) {
                Join<ConnectionRequest, AggregatorSpecialist> join = root.join("aggregatorSpecialists");
                predicates.add(criteriaBuilder.equal(join.get("id"), criteria.getAggregatorSpecialistId()));
            }
            predicates.addAll(BaseEntitySpecifications.byBaseCriteria(root,query,criteriaBuilder,criteria));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
