package az.code.copart.service;

import az.code.copart.client.AuthClient;
import az.code.copart.client.response.auth.UserResponse;
import az.code.copart.dto.request.FavouriteCarCreateRequest;
import az.code.copart.dto.request.FavouriteCarUpdateRequest;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.response.CityResponse;
import az.code.copart.dto.response.FavouriteCarResponse;
import az.code.copart.dto.response.PageableResponse;
import az.code.copart.entity.Car;
import az.code.copart.entity.City;
import az.code.copart.entity.FavouriteCar;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.FavouriteCarMapper;
import az.code.copart.repository.CarRepository;
import az.code.copart.repository.FavouriteCarRepository;
import az.code.copart.service.filter.CitySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteCarService {
    private final FavouriteCarRepository favouriteCarRepository;
    private final FavouriteCarMapper favouriteCarMapper;
    private final CarRepository carRepository;
    private final AuthClient authClient;
    // Add methods to interact with the repository and mapper as needed
    public List<FavouriteCarResponse> getAllFavouriteCars() {
        return favouriteCarRepository.findAll()
                .stream()
                .map(favouriteCarMapper::fromEntityToResponse)
                .toList();
    }
    public FavouriteCarResponse getFavouriteCarById(Long id) {
        return favouriteCarRepository.findById(id)
                .map(favouriteCarMapper::fromEntityToResponse)
                .orElseThrow(() -> CustomException.builder()
                        .message("Favourite car not found")
                        .code(404)
                        .build());
    }
    public FavouriteCarResponse saveFavouriteCar(FavouriteCarCreateRequest request) {
        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> CustomException.builder()
                .message("Car not found with id: " + request.getCarId())
                .code(404)
                .build());
        UserResponse user = authClient.getUserById(request.getUserId());

       return favouriteCarMapper.fromEntityToResponse(
               favouriteCarRepository.save(
                       favouriteCarMapper.fromCreateToEntity(request,user.getId(),car)
               ));
    }
    public FavouriteCarResponse updateFavouriteCar( FavouriteCarUpdateRequest request) {
        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> CustomException.builder()
                .message("Car not found with id: " + request.getCarId())
                .code(404)
                .build());
        UserResponse user = authClient.getUserById(request.getUserId());
        FavouriteCar favouriteCar = favouriteCarRepository.findById(request.getId()).orElseThrow(() -> CustomException.builder()
                .message("Favourite car not found with id: " + request.getId())
                .code(404)
                .build());
        return favouriteCarMapper.fromEntityToResponse(
                        favouriteCarRepository.save(
                                favouriteCarMapper.fromUpdateToEntity(favouriteCar,request,user.getId(),car)
                        ));
    }
    public void deleteFavouriteCar(Long id) {

        favouriteCarRepository.deleteById(id);
    }

}
