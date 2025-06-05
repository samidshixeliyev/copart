package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.entity.CarType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CarTypeSpecification implements Specification<CarType> {

    private final CarTypeCriteria carTypeCriteria;
    @Override
    public Predicate toPredicate(Root<CarType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(carTypeCriteria != null) {
            if(carTypeCriteria.getName() != null && !carTypeCriteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + carTypeCriteria.getName() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
