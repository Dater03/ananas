package com.example.ananas.controller;

import com.example.ananas.dto.request.UserCreateRequest;
import com.example.ananas.dto.request.UserUpdateRequest;
import com.example.ananas.dto.response.ApiResponse;
import com.example.ananas.dto.response.UserResponse;
import com.example.ananas.service.Service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public ApiResponse<UserResponse> getUserById(@PathVariable int id) {
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        String token = userService.login(user.get("username"), user.get("password"));
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        userService.forgotPassword(request.get("email"));
        return ResponseEntity.ok("Reset token sent to email");
    }

    @PostMapping("/confirm-password")
    public ResponseEntity<?> confirmPassword(@RequestBody Map<String, String> request) {
        userService.confirmPassword(request.get("token"), request.get("newPassword"));
        return ResponseEntity.ok("Password changed successfully");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> request) {
        userService.changePassword(
                request.get("username"),
                request.get("oldPassword"),
                request.get("newPassword")
        );
        return ResponseEntity.ok("Password updated successfully");
    }
}
