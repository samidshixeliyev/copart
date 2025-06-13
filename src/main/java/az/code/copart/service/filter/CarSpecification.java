package az.code.copart.service.filter;

import az.code.copart.dto.request.filter.CarCriteria;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.entity.Car;
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
public class CarSpecification implements Specification<Car> {

    private final CarCriteria carCriteria;
    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(carCriteria != null) {
            if(carCriteria.getCarType() != null && !carCriteria.getCarType().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("carType").get("name")), "%" + carCriteria.getCarType().toLowerCase() + "%"));
            }
            if(carCriteria.getFuelType() != null && !carCriteria.getFuelType().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("fuelType").get("name")), "%" + carCriteria.getFuelType().toLowerCase() + "%"));
            }
            if(carCriteria.getCity() != null&& !carCriteria.getCity().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("city").get("name"), carCriteria.getCity().toLowerCase()+"%"));
            }
            if(carCriteria.getState() != null && !carCriteria.getState().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower((root.get("city").get("state")).get("name")), "%" +carCriteria.getState().toLowerCase() + "%"));
            }
            if (carCriteria.getModel() != null && !carCriteria.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("model").get("name")), "%" + carCriteria.getModel().toLowerCase() + "%"));
            }
            if (carCriteria.getModel() != null && !carCriteria.getModel().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("model").get("name")), "%" + carCriteria.getModel().toLowerCase() + "%"));
            }
            if (carCriteria.getMaker() != null && !carCriteria.getMaker().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("maker").get("name")), "%" + carCriteria.getMaker().toLowerCase() + "%"));
            }
            if (carCriteria.getFromMileage() != null && carCriteria.getToMileage() != null) {
                predicates.add(criteriaBuilder.between(
                        root.get("mileage"),
                        carCriteria.getFromMileage(),
                        carCriteria.getToMileage()
                ));
            } else if (carCriteria.getFromMileage() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("mileage"), carCriteria.getFromMileage()));
            } else if (carCriteria.getToMileage() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("mileage"), carCriteria.getToMileage()));
            }
            if (carCriteria.getFromYear() != null && carCriteria.getToYear()!= null) {
                predicates.add(criteriaBuilder.between(root.get("year"), carCriteria.getFromYear(), carCriteria.getToYear()));
            }  else if (carCriteria.getFromYear() != null) {
                predicates.add(criteriaBuilder.greaterThan(root.get("year"), carCriteria.getFromYear()));
            }  else if (carCriteria.getToYear() != null) {
                predicates.add(criteriaBuilder.lessThan(root.get("year"), carCriteria.getToYear()));
            }

        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

