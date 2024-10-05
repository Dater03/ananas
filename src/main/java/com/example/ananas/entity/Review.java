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
@Table(name = "review")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    int messageId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "product_id")
    int productId;

    @Column(name = "rating")
    int rating;

    @Column(name = "comment")
    String comment;

    @Column(name = "created_at")
    Date createdAt;
}
