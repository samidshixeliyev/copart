package az.code.copart.controller;


import az.code.copart.service.CarImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-image")
public class CarImageController {

    private final CarImageService carImageService;

    @PostMapping("/upload/{carId}")
    public ResponseEntity<Void> saveCarImage(@RequestParam("file") List<MultipartFile> file, @PathVariable Long carId) {
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

    @DeleteMapping("/all/{id}")
    public ResponseEntity<Void> getAllCarImages(@PathVariable Long id) {
//        carImageService.downloadCarImageAll(id);
        return ResponseEntity
                .noContent()
                .build();
    }
    @GetMapping("/url/{imageId}")
    public ResponseEntity<?> getCarImageUrl(@PathVariable Long imageId) {

        return ResponseEntity
                .ok(carImageService.getCarImageUrl(imageId));
    }
    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadCarImage(@PathVariable Long imageId) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"")
                .body(carImageService.downloadCarImage(imageId));
    }

}
