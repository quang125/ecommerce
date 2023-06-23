package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long> {
    @Query("SELECT u FROM CartItem u WHERE u.cart.id =:cartId")
    List<CartItem> findByCartId(@Param("cartId") Long cartId);
    @Query("SELECT u FROM CartItem u WHERE u.cart.id =:cartId AND u.product.id =:productId")
    Optional<CartItem> findByProductIDAndCartId(@Param("cartId") Long cartId, @Param("productId") Long productId);

}
