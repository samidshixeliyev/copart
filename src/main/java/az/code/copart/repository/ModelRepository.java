package az.code.copart.repository;

import az.code.copart.entity.Model;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    boolean existsByName(@NotBlank String name);
}