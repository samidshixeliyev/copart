package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.MakerCriteria;
import az.code.copart.entity.FuelType;
import az.code.copart.entity.Maker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MakerSpecification implements Specification<Maker> {
    private final MakerCriteria makerCriteria;
    @Override
    public Predicate toPredicate(Root<Maker> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(makerCriteria != null) {
            if(makerCriteria.getName() != null && !makerCriteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + makerCriteria.getName().toLowerCase() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}