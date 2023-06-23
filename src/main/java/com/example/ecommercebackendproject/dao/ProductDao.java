package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {
    @Query("SELECT u FROM Product u WHERE u.user.id =:sellerId")
    List<Product> findAllBySeller(@Param("sellerId") Long sellerId);
    @Query("SELECT u FROM Product u WHERE u.brand =:brand")
    List<Product> findByBrand(@Param("brand") String brand);
    @Query("SELECT u FROM Product u WHERE u.category =:category")
    List<Product> findByCategory(@Param("category") String category);
    @Query("SELECT u FROM Product u WHERE u.price BETWEEN :from AND :to")
    List<Product> findByPrice(@Param("from") Integer from, @Param("to") Integer to);
}
