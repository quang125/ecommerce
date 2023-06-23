package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.dto.ReviewDto;
import com.example.ecommercebackendproject.model.entity.Review;

import java.util.List;

public interface IReviewService {
    public List<Review>getAllReviewOfCustomer(Long userId) throws Exception;
    public List<Review>getAllReviewOfAProductFromCustomer(Long userId, Long productId) throws Exception;
    public Review getReviewById(Long reviewId) throws Exception;
    public Review addReview(ReviewDto reviewDto) throws Exception;
    public void deleteReview(Long reviewId) throws Exception;
    public Review changeReview(ReviewDto reviewDto) throws Exception;
}
