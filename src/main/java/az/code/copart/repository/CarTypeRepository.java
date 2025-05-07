package az.code.copart.repository;

import az.code.copart.entity.CarType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    boolean existsByName(@NotBlank String name);
}