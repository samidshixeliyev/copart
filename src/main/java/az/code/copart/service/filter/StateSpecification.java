package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.StateCriteria;
import az.code.copart.entity.State;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class StateSpecification implements Specification<State> {

    private final StateCriteria stateCriteria;
    @Override
    public Predicate toPredicate(Root<State> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(stateCriteria != null) {
            if(stateCriteria.getName() != null && !stateCriteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + stateCriteria.getName().toLowerCase() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}