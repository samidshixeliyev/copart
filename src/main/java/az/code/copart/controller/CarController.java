package az.code.copart.controller;




import az.code.copart.dto.request.CarCreateRequest;
import az.code.copart.dto.request.CarUpdateRequest;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
public class CarController {

    private final CarService carService;


    @PostMapping
    public ResponseEntity<?> saveCar(@RequestParam(value= "file", required = false) List<MultipartFile> file,
                                     @RequestPart(name = "data")  CarCreateRequest request,@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(carService.saveCar(file, request,token))
                        .status(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllCars(){
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(carService.getAllCars())
                        .status(HttpStatus.CREATED.value())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCar(@PathVariable Long id){
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(carService.getCarById(id))
                        .status(HttpStatus.CREATED.value())
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> updateCar(@RequestParam(value= "file", required = false) List<MultipartFile> file,
                                     @RequestPart(name = "data") CarUpdateRequest request,@RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(
                BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(carService.updateCar(file, request,token))
                        .status(HttpStatus.CREATED.value())
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
