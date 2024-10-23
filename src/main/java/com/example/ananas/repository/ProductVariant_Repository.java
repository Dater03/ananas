package com.example.ananas.repository;

import com.example.ananas.entity.Product;
import com.example.ananas.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariant_Repository extends JpaRepository<ProductVariant,Integer> {
    void deleteProductVariantsByProduct(Product product);
    List<ProductVariant> findProductVariantsByProduct(Product product);
}
