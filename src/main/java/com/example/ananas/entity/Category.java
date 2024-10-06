package com.example.ananas.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    int categoryId;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "description", columnDefinition = "MEDIUMTEXT")
    String description;
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
