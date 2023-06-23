package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.Review;
import com.example.ecommercebackendproject.model.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long> {
    @Query("SELECT u FROM Review u WHERE u.user.id =:userId")
    List<Review> findAllByUserId(@Param("userId") Long userId);
    @Query("SELECT u FROM Review u WHERE u.user.id =:userId AND u.product.id =:productId")
    List<Review> findReviewOfProductByUser(@Param("userId") Long userId, @Param("productId") Long productId);
}
