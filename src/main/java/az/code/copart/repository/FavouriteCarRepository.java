package az.code.copart.repository;

import az.code.copart.entity.FavouriteCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteCarRepository extends JpaRepository<FavouriteCar, Long> {
}