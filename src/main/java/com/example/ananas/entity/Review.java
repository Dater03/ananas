package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    int reviewId;

    @Column(name = "user_id")
    int userId;

    @Column(name = "product_id")
    int productId;

    @Column(name = "rating")
    int rating;


    @Column(name = "comment", columnDefinition = "MEDIUMTEXT")
    String comment;

    @Column(name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false, nullable = false)
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable = false, updatable = false, nullable = false)
    @JsonBackReference
    Product product;

    @PrePersist
    public void handleBeforeCreate()
    {
        this.createdAt = Instant.now();
    }
}
