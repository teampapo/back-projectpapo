package com.example.backprojectpapo.util.specification;

import com.example.backprojectpapo.dto.search.OrganizationSearchCriteria;
import com.example.backprojectpapo.model.Organization;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSpecification {
    public static Specification<Organization> byCriteria(OrganizationSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getFullName() != null && !criteria.getFullName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("fullName"), "%"+criteria.getFullName()+"%"));
            }
            if (criteria.getShortName() != null && !criteria.getShortName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("shortName"), "%"+criteria.getShortName()+"%"));
            }
            if (criteria.getInn() != null && !criteria.getInn().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("inn"), "%"+criteria.getInn()+"%"));
            }
            if (criteria.getKpp() != null && !criteria.getKpp().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("kpp"), "%"+criteria.getKpp()+"%"));
            }
            if (criteria.getOgrn() != null && !criteria.getOgrn().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("ogrn"), "%"+criteria.getOgrn()+"%"));
            }
            if (criteria.getResponsiblePersonSurname() != null && !criteria.getResponsiblePersonSurname().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("responsiblePersonSurname"), "%"+criteria.getResponsiblePersonSurname()+"%"));
            }
            if (criteria.getResponsiblePersonName() != null && !criteria.getResponsiblePersonName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("responsiblePersonName"), "%"+criteria.getResponsiblePersonName()+"%"));
            }
            if (criteria.getResponsiblePersonPatronymic() != null && !criteria.getResponsiblePersonPatronymic().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("responsiblePersonPatronymic"), "%"+criteria.getResponsiblePersonPatronymic()+"%"));
            }
            if (criteria.getResponsiblePersonEmail() != null && !criteria.getResponsiblePersonEmail().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("responsiblePersonEmail"), "%"+criteria.getResponsiblePersonEmail()+"%"));
            }
            if (criteria.getResponsiblePersonPhoneNumber() != null && !criteria.getResponsiblePersonPhoneNumber().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("responsiblePersonPhoneNumber"), "%"+criteria.getResponsiblePersonPhoneNumber()+"%"));
            }

            if (criteria.getTypeOfServiceId() != null){
                predicates.add(criteriaBuilder.equal(root.get("serviceDetail").get("id") , criteria.getTypeOfServiceId()));
            }

            predicates.addAll(BaseEntitySpecifications.byBaseCriteria(root,query,criteriaBuilder,criteria));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
