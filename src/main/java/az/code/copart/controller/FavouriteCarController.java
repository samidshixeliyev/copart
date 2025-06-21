package az.code.copart.controller;

import az.code.copart.dto.request.FavouriteCarCreateRequest;
import az.code.copart.dto.request.FavouriteCarUpdateRequest;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.entity.FavouriteCar;
import az.code.copart.service.FavouriteCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favourite-car")
@RequiredArgsConstructor
public class FavouriteCarController {

    private final FavouriteCarService favouriteCarService;
    // Add methods to handle requests related to favourite cars
    @PostMapping
    public ResponseEntity<?> save(@RequestBody FavouriteCarCreateRequest request) {
        favouriteCarService.saveFavouriteCar(request);
        return new ResponseEntity<>(BaseResponse
                .builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody FavouriteCarUpdateRequest request) {
        favouriteCarService.updateFavouriteCar(request);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(favouriteCarService.getAllFavouriteCars())
                        .status(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(favouriteCarService.getFavouriteCarById(id)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        favouriteCarService.deleteFavouriteCar(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
