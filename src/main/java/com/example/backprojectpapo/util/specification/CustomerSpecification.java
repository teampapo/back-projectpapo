package com.example.backprojectpapo.util.specification;

import com.example.backprojectpapo.dto.search.CustomerSearchCriteria;
import com.example.backprojectpapo.model.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> byCriteria(CustomerSearchCriteria criteria){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getSurname() != null && !criteria.getSurname().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("surname"), "%"+criteria.getSurname()+"%"));
            }
            if (criteria.getName() != null && !criteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+criteria.getName()+"%"));
            }
            if (criteria.getPatronymic() != null && !criteria.getPatronymic().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("patronymic"), "%"+criteria.getPatronymic()+"%"));
            }
            if (criteria.getPhoneNumber() != null && !criteria.getPhoneNumber().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%"+criteria.getPhoneNumber()+"%"));
            }

            predicates.addAll(BaseEntitySpecifications.byBaseCriteria(root,query,criteriaBuilder,criteria));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
