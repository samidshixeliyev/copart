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
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarImageService {

    private final CarImageRepository carImageRepository;
    private final MinioService minioService;
    private final CarRepository carRepository;
    private final MinioProperty minioProperty;
    private final CarImageMapper carImageMapper;


    @SneakyThrows
    public List<CarImageResponse> saveCarImage(List<MultipartFile> files, Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> CustomException.builder()
                .message("Car not found with id: " + carId)
                .code(404)
                .build());

        return files.stream().map((file)-> {
            String fileName = FileUtil.generateUniqueFileName(file.getOriginalFilename());
            String minioImageUrl= minioService.uploadFile(file,fileName);
            CarImage carImage = new CarImage();
            carImage.setCar(car);
            carImage.setImageName(fileName);
            carImage.setContentType(file.getContentType());//image pdf or smthng so file tipe
            carImage.setImagePath(minioProperty.getBucket()+"/"+fileName);
            return carImageMapper.fromEntityToResponse(carImageRepository.save(carImage),minioImageUrl);
        }).toList();
    }


    public void deleteByCarImageId(Long id) {
        carImageRepository.findById(id).ifPresent(carImage -> {
            minioService.deleteFile(carImage.getImageName());
            carImageRepository.delete(carImage);
        });
    }

    public String getCarImageUrl(Long imageId) {
        CarImage carImage = carImageRepository.findById(imageId).orElseThrow(() -> CustomException.builder()
                .message("CarImage not found with id: " + imageId)
                .code(404)
                .build());
        return minioService.getFileUrl(carImage.getImageName());
    }
    public InputStreamResource downloadCarImage(Long imageId) {
        CarImage carImage = carImageRepository.findById(imageId).orElseThrow(() -> CustomException.builder()
                .message("CarImage not found with id: " + imageId)
                .code(404)
                .build());
        InputStream inputStream = minioService.downloadFile(carImage.getImageName());
        InputStreamResource resource = new InputStreamResource(inputStream);
        return resource;
    }

}
