package az.code.copart.controller;


import az.code.copart.dto.request.CarCreateRequest;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
public class CarController {

    private final CarService carService;

    @PostMapping(consumes = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveCar(@RequestPart(name="file", required = false) MultipartFile file,
                                     @RequestPart(name="data", required = false) @Valid CarCreateRequest request) {

        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(carService.saveCar(file, request))
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.OK);
    }


}
