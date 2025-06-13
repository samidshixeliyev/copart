package az.code.copart.repository;

import az.code.copart.entity.CarType;
import az.code.copart.entity.City;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CityRepository extends JpaRepository<City, Long> , JpaSpecificationExecutor<City> {
    boolean existsByName(@NotBlank String name);
}