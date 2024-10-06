package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    int productId;

    @Column(name = "product_name")
    String productName;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    String description;

    @Column(name = "price")
    double price;

    @Column(name = "stock")
    int stock;

    @Column(name = "discount")
    double discount;

    @Column(name = "sold_quantity")
    int soldQuantity;

    @Column(name = "image")
    String image;

    @Column(name = "size")
    int size;

    @Column(name = "color")
    String color;

    @Column(name = "material")
    String material;

    private Instant createdAt;
    private Instant updateAt;

    @PrePersist
    public void handleBeforeCreate()
    {
        this.createdAt = Instant.now();
    }
    @PreUpdate
    public void handleBeforeUpdate()
    {
        this.updateAt = Instant.now();
    }

}
