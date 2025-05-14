package az.code.copart.controller;

import az.code.copart.dto.request.UserCreateRequest;
import az.code.copart.dto.response.BaseResponse;
import az.code.copart.repository.UserRepository;
import az.code.copart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    // Add your endpoints here
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserCreateRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(userService.saveUser(request))
                .message("Success")
                .build(), HttpStatus.OK);
    }

}
