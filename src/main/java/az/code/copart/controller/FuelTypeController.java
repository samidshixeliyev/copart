package az.code.copart.controller;

import az.code.copart.dto.request.FuelTypeCreateRequest;
import az.code.copart.dto.request.FuelTypeUpdateRequest;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.entity.FuelType;
import az.code.copart.service.FuelTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/fuel-type")
@RequiredArgsConstructor
public class FuelTypeController {
 private final FuelTypeService fuelTypeService;
    // Add methods to handle requests related to fuel types
    @PostMapping
    public ResponseEntity<?> save(@RequestBody FuelTypeCreateRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.CREATED.value())
                .data(fuelTypeService.saveFuelType(request))
                .build(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody FuelTypeUpdateRequest request) {
        fuelTypeService.updateFuelType(request);
        return ResponseEntity
                .ok()
                .build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(fuelTypeService.findAll())
                        .status(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(fuelTypeService.findById(id)).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        fuelTypeService.deleteFuelType(id);
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .status(HttpStatus.NO_CONTENT.value())
                        .build());
    }
}
