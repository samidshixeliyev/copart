package az.code.copart.repository;

import az.code.copart.entity.CarType;
import az.code.copart.entity.Maker;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MakerRepository extends JpaRepository<Maker, Long>, JpaSpecificationExecutor<Maker> {
    boolean existsByName(@NotBlank String name);
}