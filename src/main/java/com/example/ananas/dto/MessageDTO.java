package com.example.ananas.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageDTO {
    int messageId;
    int senderId;
    int receiverId;
    String message;
    Date createdAt;
}
