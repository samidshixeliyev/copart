package az.code.copart.service;


import az.code.copart.config.MinioProperty;
import az.code.copart.dto.response.CarImageResponse;
import az.code.copart.entity.Car;
import az.code.copart.entity.CarImage;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.CarImageMapper;
import az.code.copart.repository.CarImageRepository;
import az.code.copart.repository.CarRepository;
import az.code.copart.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository carImageRepository;
    private final MinioService minioService;
    private final CarRepository carRepository;
    private final MinioProperty minioProperty;
    private final CarImageMapper carImageMapper;


    public CarImageResponse saveCarImage(MultipartFile file, Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> CustomException.builder()
                .message("Car not found with id: " + carId)
                .code(404)
                .build());

        String fileName = FileUtil.generateUniqueFileName(file.getOriginalFilename());

        minioService.uploadFile(file,fileName);

        CarImage carImage = new CarImage();
        carImage.setCar(car);
        carImage.setImageName(fileName);
        carImage.setImagePath(minioProperty.getBucket()+"/"+fileName);
        return carImageMapper.fromEntityToResponse(carImageRepository.save(carImage));
    }


    public void deleteByCarImageId(Long id) {
        carImageRepository.findById(id).ifPresent(carImage -> {
            minioService.deleteFile(carImage.getImageName());
            carImageRepository.delete(carImage);
        });
    }

}
