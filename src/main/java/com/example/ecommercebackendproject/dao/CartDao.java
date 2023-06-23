package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
    @Query("SELECT u FROM Cart u WHERE u.user.id =:userId")
    public Cart findCartByUserId(@Param("userId") Long userId);
}
