package az.code.copart.repository;

import az.code.copart.entity.CarType;
import az.code.copart.entity.State;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StateRepository extends JpaRepository<State, Long> , JpaSpecificationExecutor<State> {
    boolean existsByName(@NotBlank String name);
}