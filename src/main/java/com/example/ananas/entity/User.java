package com.example.ananas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Data
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

    @Column(name = "firstname")
    String firstname;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "avatar")
    String avatar;

    @ElementCollection
    Set<String>roles;
}
