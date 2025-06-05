package az.code.copart.repository;

import az.code.copart.entity.CarType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CarTypeRepository extends JpaRepository<CarType, Long>, JpaSpecificationExecutor<CarType> {
    boolean existsByName(@NotBlank String name);
}