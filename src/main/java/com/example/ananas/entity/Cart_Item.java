package com.example.ananas.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart_item")
public class Cart_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_item_id")
    int cardItemId;

    @Column(name = "card_id")
    int cardId;

    @Column(name = "product_id")
    int productId;

    @Column(name = "quantity")
    int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

}
