package az.code.copart.controller;

import az.code.copart.dto.request.MakerCreateRequest;
import az.code.copart.dto.request.MakerUpdateRequest;
import az.code.copart.dto.request.filter.CityCriteria;
import az.code.copart.dto.request.filter.MakerCriteria;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.entity.Maker;
import az.code.copart.service.MakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maker")
@RequiredArgsConstructor
public class MakerController {
    private final MakerService makerService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MakerCreateRequest request,@RequestHeader String authorization) {
        String token = authorization.substring(7);
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(makerService.saveMaker(request,token))
                .status(HttpStatus.CREATED.value())
                .build(), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody MakerUpdateRequest request,@RequestHeader String authorization) {
        String token = authorization.substring(7);
        makerService.updateMaker(request,token);
        return ResponseEntity
                .ok()
                .build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(makerService.findAll())
                        .status(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable, MakerCriteria criteria) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(makerService.getAllMakers(pageable, criteria))
                .status(HttpStatus.OK.value())
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(makerService.findById(id)).build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,@RequestHeader String authorization) {
        String token = authorization.substring(7);
        makerService.deleteMaker(id,token);
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .status(HttpStatus.NO_CONTENT.value())
                        .build());
    }
}
