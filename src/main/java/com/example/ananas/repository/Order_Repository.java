package com.example.ananas.repository;

import com.example.ananas.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_Repository extends JpaRepository<Order, Integer> {
}
