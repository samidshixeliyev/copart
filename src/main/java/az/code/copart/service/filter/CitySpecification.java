package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.entity.CarType;
import az.code.copart.entity.City;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CitySpecification implements Specification<City> {

    private final CityCriteria cityCriteria;
    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(cityCriteria != null) {
            if(cityCriteria.getName() != null && !cityCriteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + cityCriteria.getName().toLowerCase() + "%"));
            }
            if(cityCriteria.getStateId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("state").get("id"), cityCriteria.getStateId()));
            }
            if(cityCriteria.getStateName() != null && !cityCriteria.getStateName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower((root.get("state").get("name"))), "%" +cityCriteria.getStateName().toLowerCase() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
