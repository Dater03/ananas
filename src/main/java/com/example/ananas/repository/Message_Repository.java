package com.example.ananas.repository;

import com.example.ananas.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Message_Repository extends JpaRepository<Messages, Integer> {
}
