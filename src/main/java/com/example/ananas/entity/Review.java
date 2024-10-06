package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false, updatable = false, nullable = false)
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "product_id",insertable = false, updatable = false, nullable = false)
    @JsonBackReference
    Product product;
}
