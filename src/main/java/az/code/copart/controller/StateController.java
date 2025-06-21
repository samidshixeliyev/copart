package az.code.copart.controller;

import az.code.copart.dto.request.StateCreateRequest;
import az.code.copart.dto.request.StateUpdateRequest;
import az.code.copart.dto.request.filter.StateCriteria;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/state")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    // Add methods to handle requests related to states
    @PostMapping
    public ResponseEntity<?> save(@RequestBody StateCreateRequest request) {
        return ResponseEntity
                .status(201)
                .body(stateService.saveState(request));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody StateUpdateRequest request) {
        stateService.updateState(request);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(StateCriteria criteria) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(stateService.getAllStatesWithCriteria(criteria))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(stateService.getStateById(id))
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stateService.deleteState(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
