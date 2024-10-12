package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_item")
public class Order_Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference("order-orderItems")
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("product-orderItems")
    Product product;

    @Column(name = "quantity", nullable = false)
    Integer quantity;

    @Column(name = "price", nullable = false)
    BigDecimal price;
}

