package az.code.copart.repository;

import az.code.copart.entity.Maker;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakerRepository extends JpaRepository<Maker, Long> {
    boolean existsByName(@NotBlank String name);
}