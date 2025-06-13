package az.code.copart.repository;

import az.code.copart.entity.Car;
import az.code.copart.entity.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> , JpaSpecificationExecutor<Car> {

}
