package az.code.copart.controller;

import az.code.copart.dto.request.CarTypeCreateRequest;
import az.code.copart.dto.request.CarTypeUpdateRequest;
import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.entity.CarType;
import az.code.copart.service.CarTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/car-type")
@RequiredArgsConstructor
public class CarTypeController {
    private final CarTypeService carTypeService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CarTypeCreateRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(carTypeService.saveCarType(request))
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody CarTypeUpdateRequest request) {
        carTypeService.updateCarType(request);
        return ResponseEntity
                .ok()
                .build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(carTypeService.getAllCarTypes())
                        .status(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable, CarTypeCriteria criteria) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(carTypeService.getAllCarTypes(pageable, criteria))
                .status(HttpStatus.OK.value())
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(carTypeService.getCarTypeById(id)).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        carTypeService.deleteCarType(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
