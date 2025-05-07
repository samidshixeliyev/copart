package az.code.copart.repository;

import az.code.copart.entity.FuelType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    boolean existsByName(@NotBlank String name);
}