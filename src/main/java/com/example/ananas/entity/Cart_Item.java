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
@Table(name = "cart_item")
public class Cart_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    int cartItemId;

//    @Column(name = "cart_id")
//    int cartId;
//
//    @Column(name = "product_id")
//    int productId;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "card_id")
    int cardId;

    @Column(name = "product_id")
    int productId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cart_id", nullable = false,updatable = false, insertable = false)
    Cart cart;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", nullable = false,updatable = false, insertable = false)
    Product product;

}
