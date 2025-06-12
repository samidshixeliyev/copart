package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.ModelCriteria;
import az.code.copart.entity.City;
import az.code.copart.entity.Model;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


    @RequiredArgsConstructor
    public class ModelSpecification implements Specification<Model> {

        private final ModelCriteria modelCriteria;
        @Override
        public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<>();
            if(modelCriteria != null) {
                if(modelCriteria.getName() != null && !modelCriteria.getName().isEmpty()) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + modelCriteria.getName().toLowerCase() + "%"));
                }
                if(modelCriteria.getMakerId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("maker").get("id"), modelCriteria.getMakerId()));
                }
                if(modelCriteria.getMakerName()!= null && !modelCriteria.getMakerName().isEmpty()) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower((root.get("state").get("name"))), "%" +modelCriteria.getMakerName().toLowerCase() + "%"));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }
    }

