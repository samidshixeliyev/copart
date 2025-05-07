package az.code.copart.repository;

import az.code.copart.entity.State;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
    boolean existsByName(@NotBlank String name);
}