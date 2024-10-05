package com.example.ananas.repository;

import com.example.ananas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository  extends JpaRepository<User, Integer> {
}
