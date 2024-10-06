package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int userId;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "address")
    String address;

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "avatar")
    String avatar;

    @ElementCollection
    Set<String>roles;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-messages")
    List<Messages> messages;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-cart")
    List<Cart> cart;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-review")
    List<Review> review;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-order")
    List<Order> order;
}
