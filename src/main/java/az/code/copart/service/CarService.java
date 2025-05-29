package az.code.copart.service;

import az.code.copart.client.AuthClient;
import az.code.copart.client.response.auth.UserResponse;
import az.code.copart.dto.request.CarCreateRequest;
import az.code.copart.dto.request.CarUpdateRequest;
import az.code.copart.dto.response.CarResponse;
import az.code.copart.entity.*;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.CarMapper;
import az.code.copart.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final MakerRepository makerRepository;
    private final ModelRepository modelRepository;
    private final CarTypeRepository carTypeRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final CarImageService carImageService;
    private final MinioService minioService;
    private final CarImageRepository carImageRepository;
    private final AuthClient authClient;

    public List<CarResponse> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(carMapper::fromEntityToResponse)
                .peek(t
                        -> t.getCarImage().forEach(c
                                -> c.setImagePath(minioService.getFileUrl(c.getImageName()))))
                .toList();
    }

    public CarResponse getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::fromEntityToResponse)
                .map(carResponse -> {
                    carResponse
                            .getCarImage()
                            .forEach(image->image.setImagePath(
                                    minioService.getFileUrl(image.getImageName())));
                return carResponse;})
                .orElseThrow(() -> CustomException.builder()
                        .message("Car not found with id: " + id)
                        .code(404)
                        .build());

    }

    public CarResponse saveCar(List<MultipartFile> file, CarCreateRequest request,String token) {
        Maker maker = makerRepository.findById(request.getMakerId()).orElseThrow(() -> CustomException.builder()
                .message("Maker not found" + request.getMakerId())
                .code(404)
                .build());
        Model model = modelRepository.findById(request.getModelId()).orElseThrow(() -> CustomException.builder()
                .message("Model not found" + request.getModelId())
                .code(404)
                .build());
        CarType carType = carTypeRepository.findById(request.getCarTypeId()).orElseThrow(() -> CustomException.builder()
                .message("CarType not found" + request.getCarTypeId())
                .code(404)
                .build());
        FuelType fuelType = fuelTypeRepository.findById(request.getFuelTypeId()).orElseThrow(() -> CustomException.builder()
                .message("FuelType not found" + request.getFuelTypeId())
                .code(404)
                .build());
        City city = cityRepository.findById(request.getCityId()).orElseThrow(() -> CustomException.builder()
                .message("City not found" + request.getCityId())
                .code(404)
                .build());
        UserResponse user = authClient.getUserById(request.getUserId());
        CarResponse carResponse = carMapper.fromEntityToResponse(
                carRepository.save(
                        carMapper.fromCreateToEntity(request, maker, model, carType, fuelType, city, user.getId())
                ));
        carResponse.setCarImage(carImageService.saveCarImage(file, carResponse.getId()));
        return carResponse;

    }

    public CarResponse updateCar(List<MultipartFile> file, CarUpdateRequest request,String token) {
        Maker maker = makerRepository.findById(request.getMakerId()).orElseThrow(() -> CustomException.builder()
                .message("Maker not found" + request.getMakerId())
                .code(404)
                .build());
        Model model = modelRepository.findById(request.getModelId()).orElseThrow(() -> CustomException.builder()
                .message("Model not found" + request.getModelId())
                .code(404)
                .build());
        CarType carType = carTypeRepository.findById(request.getCarTypeId()).orElseThrow(() -> CustomException.builder()
                .message("CarType not found" + request.getCarTypeId())
                .code(404)
                .build());
        FuelType fuelType = fuelTypeRepository.findById(request.getFuelTypeId()).orElseThrow(() -> CustomException.builder()
                .message("FuelType not found" + request.getFuelTypeId())
                .code(404)
                .build());
        City city = cityRepository.findById(request.getCityId()).orElseThrow(() -> CustomException.builder()
                .message("City not found" + request.getCityId())
                .code(404)
                .build());
        UserResponse user = authClient.getUserById(request.getUserId());
        Car car = carRepository.findById(request.getId()).orElseThrow(() -> CustomException.builder()
                .message("Car not found with id: " + request.getId())
                .code(404)
                .build());
        CarResponse carResponse = carMapper.fromEntityToResponse(
                carRepository.save(
                        carMapper.fromUpdateToEntity(car, request, maker, model, carType, fuelType, city, user.getId())
                ));
        carResponse.getCarImage().addAll(carImageService.saveCarImage(file, carResponse.getId()));
        return carResponse;
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

}
