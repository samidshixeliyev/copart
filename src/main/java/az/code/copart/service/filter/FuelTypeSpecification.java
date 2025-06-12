package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.FuelTypeCriteria;
import az.code.copart.entity.FuelType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FuelTypeSpecification implements Specification<FuelType> {

    private final FuelTypeCriteria fuelTypeCriteria;
    @Override
    public Predicate toPredicate(Root<FuelType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(fuelTypeCriteria != null) {
            if(fuelTypeCriteria.getName() != null && !fuelTypeCriteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + fuelTypeCriteria.getName().toLowerCase() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}