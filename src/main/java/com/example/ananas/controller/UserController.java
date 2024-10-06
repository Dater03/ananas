package com.example.ananas.controller;

import com.example.ananas.dto.request.UserCreateRequest;
import com.example.ananas.dto.request.UserUpdateRequest;
import com.example.ananas.dto.response.ApiResponse;
import com.example.ananas.dto.response.UserResponse;
import com.example.ananas.service.Service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> addUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userCreateRequest))
                .code(200)
                .build();
    }
    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .code(200)
                .build();
    }
    @GetMapping("{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable int id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .code(200)
                .build();
    }
    @PutMapping
    public ApiResponse<UserResponse> updateUser(@RequestParam int id,@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, userUpdateRequest))
                .code(200)
                .build();
    }
    @DeleteMapping
    public ApiResponse<String> deleteUserById(@RequestParam int id) {
        return ApiResponse.<String>builder()
                .result(userService.deleteUser(id))
                .code(200)
                .build();
    }
    @GetMapping("/users")
    public ApiResponse<List<UserResponse>> getUsersByEmail(@RequestParam String email) {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getEmail(email))
                .code(200)
                .build();
    }
}
