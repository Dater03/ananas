package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    int cartId;

    @Column(name = "user_id")
    int userId;

    @ManyToOne
    @JsonBackReference("user-cart")
    @JoinColumn(name = "user_id",insertable = false, updatable = false, nullable = false)
    User user;

    @OneToMany(mappedBy = "cart")
    @JsonManagedReference
    List<Cart_Item> cartItem;

}
