package com.example.ananas.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    int messageId;

    @Column(name = "sender_id")
    int senderId;

    @Column(name = "receiver_id")
    int receiverId;

    @Column(name = "message")
    String message;

    @Column(name = "created_at")
    Date createdAt;
}
