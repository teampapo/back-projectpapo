package com.example.backprojectpapo.util.specification;

import com.example.backprojectpapo.dto.search.ServiceDetailSearchCriteria;
import com.example.backprojectpapo.model.ServiceDetail;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ServiceDetailSpecification {
    public static Specification<ServiceDetail> byCriteria(ServiceDetailSearchCriteria criteria){
      return (root, query, criteriaBuilder) -> {
          List<Predicate> predicates = new ArrayList<>();

          if (criteria.getOrganizationId() != null){
              predicates.add(criteriaBuilder.equal(root.get("organization").get("id"), criteria.getOrganizationId()));
          }
          if (criteria.getTypeId() != null){
              predicates.add(criteriaBuilder.equal(root.get("typeOfService").get("id"), criteria.getTypeId()));
          }
          if (criteria.getCode() != null && !criteria.getCode().isEmpty()){
              predicates.add(criteriaBuilder.like(root.get("code"), "%"+criteria.getCode()+"%"));
          }
          if (criteria.getName() != null && !criteria.getName().isEmpty()){
              predicates.add(criteriaBuilder.like(root.get("name"), "%"+criteria.getName()+"%"));
          }
          if (criteria.getCost() != null){
              predicates.add(criteriaBuilder.equal(root.get("cost"), criteria.getCost()));
          }
          if (criteria.getDuration() != null){
              predicates.add(criteriaBuilder.equal(root.get("duration"), criteria.getDuration()));
          }
          if (criteria.getTypeName() != null && !criteria.getTypeName().isEmpty()){
              predicates.add(criteriaBuilder.like(root.get("typeOfService").get("name"), "%"+criteria.getTypeName()+"%"));
          }
          if (criteria.getTypeCode() != null && !criteria.getTypeCode().isEmpty()){
              predicates.add(criteriaBuilder.like(root.get("typeOfService").get("code"), "%"+criteria.getTypeName()+"%"));
          }

          predicates.addAll(BaseEntitySpecifications.byBaseCriteria(root,query,criteriaBuilder,criteria));
          return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
      };
    }
}
