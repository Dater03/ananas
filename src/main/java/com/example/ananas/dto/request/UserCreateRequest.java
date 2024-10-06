package com.example.ananas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {
    @Size(min = 3, message = "INVALID_USERNAME")
    String username;
    @Size(min = 5, message = "INVALID_PASSWORD")
    String password;
    @Email(message = "EMAIL_NOT_EXISTED")
    String email;
    String address;
}
