package com.example.ananas.service.Service;

import com.example.ananas.dto.request.UserCreateRequest;
import com.example.ananas.dto.request.UserUpdateRequest;
import com.example.ananas.dto.response.UserResponse;
import com.example.ananas.entity.Role;
import com.example.ananas.entity.User;
import com.example.ananas.exception.AppException;
import com.example.ananas.exception.ErrException;
import com.example.ananas.mapper.IUserMapper;
import com.example.ananas.repository.User_Repository;
import com.example.ananas.service.IService.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    User_Repository userRepository;
    IUserMapper userMapper;
    PasswordEncoder passwordEncoder;
    EmailService emailService;
    private final AuthenticationService authenticationService;

    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        if (userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new AppException(ErrException.USER_EXISTED);
        }
        if (userRepository.existsByEmail(userCreateRequest.getEmail())) {
            throw new AppException(ErrException.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(userCreateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<String> roles = new HashSet<>(); // HashSet đảm bảo rằng mỗi vai trò của người dùng là duy nhất,tối ưu hóa các thao tác và kiểm tra vai trò
        roles.add(Role.User.name()); // cho phep user them nguoidungmoi
        user.setRoles(roles);
        if (userCreateRequest.getEmail() != null && !userCreateRequest.getEmail().isEmpty()) {
            String subject = "Welcome to our service";
            String text = "Dear "+userCreateRequest.getUsername()+","+userCreateRequest.getEmail()+","+userCreateRequest.getPassword();
            emailService.sendMessage(userCreateRequest.getEmail(), subject, text);
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(int id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrException.USER_NOT_EXISTED));
        userMapper.updateUser(user, userUpdateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PostAuthorize("hasRole('Admin')")
    public String deleteUser(int id) {
        User userDelete = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrException.USER_NOT_EXISTED));
        userRepository.delete(userDelete);
        return "xoa thanh cong";
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserbyId(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrException.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    private boolean isPhoto(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    private String storeFile(MultipartFile file) {
        if (!isPhoto(file) || file.getOriginalFilename() == null || file.getOriginalFilename().isEmpty()) {
            throw new AppException(ErrException.NOT_FILE);
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID().toString()+"_"+fileName;
        java.nio.file.Path uploadDir = java.nio.file.Paths.get("upload");
        if (!Files.exists(uploadDir)) {
            try {
                Files.createDirectories(uploadDir);
            } catch (IOException e) {
                throw new AppException(ErrException.DIRECTORY_CREATION_FAILED);
            }
        }
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new AppException(ErrException.FILE_STORAGE_FAILED);
        }
        return uniqueFilename;
    }

    public UserResponse uploadAvatar(int id, MultipartFile file) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrException.USER_NOT_EXISTED));
        String fileName = storeFile(file);
        user.setAvatar(fileName);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public Optional<UserResponse> getEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserResponse);
    }

    // Đăng nhập và tạo JWT
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = authenticationService.createToken(user);

        return token;
    }


    // Quên mật khẩu - Gửi mã xác nhận qua email
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String subject = "Dear, "+user.getUsername()+", your account has been reset token successfully.";
        String resetToken = UUID.randomUUID().toString();
        emailService.sendMessage(email, subject , resetToken);
    }

    // Xác nhận tạo mật khẩu mới qua token
    // chua chay
    public void confirmPassword(String token, String newPassword) {
        User user = validateToken(token);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        // Xóa token sau khi sử dụng để reset mật khẩu
    }

    // Đổi mật khẩu
    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private User validateToken(String token) {
        String username;
        try {
            username = authenticationService.decodeToken(token);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid token");
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for the provided token"));
    }

}
