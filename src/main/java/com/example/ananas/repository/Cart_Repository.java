package com.example.ananas.repository;

import com.example.ananas.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_Repository extends JpaRepository<Cart, Integer> {

}
