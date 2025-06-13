package az.code.copart.controller;

import az.code.copart.dto.request.CityCreateRequest;
import az.code.copart.dto.request.CityUpdateRequest;
import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.entity.City;
import az.code.copart.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    // Implement the methods for handling city-related requests here
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CityCreateRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(cityService.saveCity(request))
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody CityUpdateRequest request) {
        cityService.updateCity(request);
        return ResponseEntity
                .ok()
                .build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(cityService.findAll())
                        .status(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable, CityCriteria criteria) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(cityService.getAllCities(pageable, criteria))
                .status(HttpStatus.OK.value())
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(cityService.findById(id)).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
