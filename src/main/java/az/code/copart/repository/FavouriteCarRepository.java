package az.code.copart.repository;

import az.code.copart.entity.City;
import az.code.copart.entity.FavouriteCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FavouriteCarRepository extends JpaRepository<FavouriteCar, Long> , JpaSpecificationExecutor<FavouriteCar> {
}