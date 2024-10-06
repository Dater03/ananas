package com.example.ananas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "images")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product_Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;

}
