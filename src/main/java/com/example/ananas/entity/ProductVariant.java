package com.example.ananas.entity;

import com.example.ananas.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product_variant")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    int variantId;

    @Column(name = "size")
    int size;

    @Column(name = "color",columnDefinition = "TEXT")
    String color;

    @Column(name = "stock")
    int stock;

    @OneToMany(mappedBy = "productVariant")
    @JsonManagedReference
    List<Cart_Item> cartItems;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    Product product;

}
