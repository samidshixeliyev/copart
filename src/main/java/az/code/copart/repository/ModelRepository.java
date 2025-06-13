package az.code.copart.repository;

import az.code.copart.entity.CarType;
import az.code.copart.entity.Model;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModelRepository extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {
    boolean existsByName(@NotBlank String name);
}