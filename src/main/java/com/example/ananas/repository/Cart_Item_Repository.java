package com.example.ananas.repository;

import com.example.ananas.entity.Cart_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_Item_Repository extends JpaRepository<Cart_Item, Integer> {
}
