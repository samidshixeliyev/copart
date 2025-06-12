package az.code.copart.controller;

import az.code.copart.dto.request.ModelCreateRequest;
import az.code.copart.dto.request.ModelUpdateRequest;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/copart/model")
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;
    // Add methods to handle requests related to models
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ModelCreateRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(modelService.saveModel(request))
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ModelUpdateRequest request) {
        modelService.updateModel(request);
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(modelService.getModelById(id)).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        modelService.deleteModel(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
