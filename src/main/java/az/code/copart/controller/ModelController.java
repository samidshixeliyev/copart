package az.code.copart.controller;

import az.code.copart.dto.request.ModelCreateRequest;
import az.code.copart.dto.request.ModelUpdateRequest;
import az.code.copart.dto.request.filter.CarTypeCriteria;
import az.code.copart.dto.request.filter.ModelCriteria;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    // Add methods to handle requests related to models
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ModelCreateRequest request,@RequestHeader String authorization) {
        String token = authorization.substring(7); // "Bearer " silinir
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(modelService.saveModel(request,token))
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ModelUpdateRequest request,@RequestHeader String authorization) {
        String token = authorization.substring(7);
        modelService.updateModel(request,token);
        return ResponseEntity
                .ok()
                .build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(modelService.getAllModels())
                        .status(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable, ModelCriteria criteria) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(modelService.getAllModels(pageable, criteria))
                .status(HttpStatus.OK.value())
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(modelService.getModelById(id)).build());
    }
    @DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id,@RequestHeader String authorization) {
        String token = authorization.substring(7);
        modelService.deleteModel(id,token);
        return ResponseEntity
                .ok()
                .build();
    }
}
