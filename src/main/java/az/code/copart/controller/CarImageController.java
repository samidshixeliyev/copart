package az.code.copart.controller;


import az.code.copart.service.CarImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-image")
public class CarImageController {

    private final CarImageService carImageService;

    @PostMapping("/upload/{carId}")
    public ResponseEntity<Void> saveCarImage(@RequestParam("file") MultipartFile file, @PathVariable Long carId) {
        carImageService.saveCarImage(file, carId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarImage(@PathVariable Long id) {
        carImageService.deleteByCarImageId(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
