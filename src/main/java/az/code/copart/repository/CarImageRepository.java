package az.code.copart.repository;

import az.code.copart.entity.Car;
import az.code.copart.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {
    Set<CarImage> findAllByIdIn(Set<Long> ids);
}