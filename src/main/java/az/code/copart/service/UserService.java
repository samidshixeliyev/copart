package az.code.copart.service;

import az.code.copart.dto.request.UserCreateRequest;
import az.code.copart.dto.request.UserUpdateRequest;
import az.code.copart.dto.response.UserResponse;
import az.code.copart.entity.User;
import az.code.copart.handler.CustomException;
import az.code.copart.mapper.UserMapper;
import az.code.copart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    // Add your service methods here
    // Example method to save a user
    public UserResponse saveUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw CustomException.builder()
                    .message("User already exists")
                    .code(409)
                    .build();
        }
        if(userRepository.existsByUsername((request.getUsername()))){
            throw CustomException.builder()
                    .message("User already exists")
                    .code(409)
                    .build();
        }
        User user = userMapper.fromCreateToEntity(request);
        userRepository.save(user);
        return userMapper.fromEntityToResponse(user);
    }
    public UserResponse updateUser(UserUpdateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw CustomException.builder()
                    .message("User already exists")
                    .code(409)
                    .build();
        }
        if(userRepository.existsByUsername((request.getUsername()))) {
            throw CustomException.builder()
                    .message("User already exists")
                    .code(409)
                    .build();
        }
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> CustomException
                        .builder()
                        .message("User not found")
                        .code(404)
                        .build());
        userMapper.fromUpdateToEntity(user, request);
        userRepository.save(user);
        return userMapper.fromEntityToResponse(user);
    }
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::fromEntityToResponse)
                .toList();
    }
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> CustomException
                        .builder()
                        .message("User not found")
                        .code(404)
                        .build());
        return userMapper.fromEntityToResponse(user);
    }
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> CustomException
                        .builder()
                        .message("User not found")
                        .code(404)
                        .build());
        userRepository.delete(user);
    }
}
