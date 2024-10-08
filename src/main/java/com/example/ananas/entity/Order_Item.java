package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_item")
public class Order_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    int orderitemId;

    @Column(name = "order_id")
    int orderId;

    @Column(name = "product_id")
    int productId;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "price")
    double price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", insertable = false, updatable = false, nullable = false)
    Product product;


}
