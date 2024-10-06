package com.example.ananas.repository;

import com.example.ananas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface User_Repository  extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);

    // JPQL
    @Query("SELECT u from User u where u.email=:email")
    List<User> findByEmail(@Param("email") String email);

    // native query
    @Query(value = "SELECT * from User where username=:username",nativeQuery = true)
    List<User> findByUsernameNative(@Param("username") String username);

    boolean existsByEmail(String email);
}
