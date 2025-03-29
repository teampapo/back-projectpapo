package com.example.backprojectpapo.util.specification;

import com.example.backprojectpapo.dto.search.BaseEntitySearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BaseEntitySpecifications {
    public static <T> List<Predicate> byBaseCriteria(Root<T> root, CriteriaQuery<?> query,
                                                      CriteriaBuilder criteriaBuilder,
                                                      BaseEntitySearchCriteria criteria) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getId() != null){
            predicates.add(criteriaBuilder.equal(root.get("id"), criteria.getId()));
        }
        if (criteria.getDistinct()) {
            query.distinct(true);
        }
        return predicates;
    }
}
