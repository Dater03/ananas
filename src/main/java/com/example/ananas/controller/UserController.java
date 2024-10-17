package com.example.ananas.controller;

import com.example.ananas.dto.request.UserCreateRequest;
import com.example.ananas.dto.request.UserUpdateRequest;
import com.example.ananas.dto.response.ApiResponse;
import com.example.ananas.dto.response.UserResponse;
import com.example.ananas.service.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> addUser(@RequestBody UserCreateRequest userCreateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(userCreateRequest))
                .code(200)
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .code(200)
                .build();
    }

    @GetMapping({"{id}"})
    public ApiResponse<UserResponse> getUserById(@PathVariable Integer id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserbyId(id))
                .code(200)
                .build();
    }

    @PutMapping
    public ApiResponse<UserResponse> updateUser(@RequestParam int id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(id, userUpdateRequest))
                .code(200)
                .build();
    }

    @PutMapping("/photo/{id}")
    public ApiResponse<UserResponse> updateUserPhoto(@PathVariable("id") int id,@RequestParam("avatar") MultipartFile file) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.uploadAvatar(id, file))
                .code(200)
                .build();
    }

    @DeleteMapping
    public ApiResponse<String> deleteUser(@RequestParam int id) {
        return ApiResponse.<String>builder()
                .result(userService.deleteUser(id))
                .code(200)
                .build();
    }
}
