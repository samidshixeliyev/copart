package az.code.copart.repository;

import az.code.copart.entity.CarType;
import az.code.copart.entity.FuelType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> , JpaSpecificationExecutor<FuelType> {
    boolean existsByName(@NotBlank String name);
}