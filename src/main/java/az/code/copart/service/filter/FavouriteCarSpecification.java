package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.FavouriteCarCriteria;
import az.code.copart.entity.City;
import az.code.copart.entity.FavouriteCar;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class FavouriteCarSpecification implements Specification<FavouriteCar> {

    private final FavouriteCarCriteria favouriteCarCriteria;
    @Override
    public Predicate toPredicate(Root<FavouriteCar> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(favouriteCarCriteria != null) {
            if(favouriteCarCriteria.getName() != null && !favouriteCarCriteria.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + favouriteCarCriteria.getName().toLowerCase() + "%"));
            }
            if(favouriteCarCriteria.getStateId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("state").get("id"), favouriteCarCriteria.getStateId()));
            }
            if(favouriteCarCriteria.getStateName() != null && !favouriteCarCriteria.getStateName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower((root.get("state").get("name"))), "%" +favouriteCarCriteria.getStateName().toLowerCase() + "%"));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}