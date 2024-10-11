package com.example.ananas.repository;

import com.example.ananas.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Voucher_Repository extends JpaRepository<Voucher, Integer> {
}
