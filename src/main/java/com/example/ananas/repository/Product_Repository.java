package com.example.ananas.repository;

import com.example.ananas.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_Repository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


    List<Product> findTop4ByOrderBySoldQuantityDesc();
}
