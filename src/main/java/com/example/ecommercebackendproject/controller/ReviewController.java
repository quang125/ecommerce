package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.dto.ReviewDto;
import com.example.ecommercebackendproject.model.entity.Review;
import com.example.ecommercebackendproject.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private IReviewService reviewService;
    @GetMapping("/review/show/{userId}")
    public List<Review> showAllReviewOfUser(@Param("userId") Long userId) throws Exception {
        return reviewService.getAllReviewOfCustomer(userId);
    }
    @GetMapping("/review/show/{userId}/{productId}")
    public List<Review> showAllReviewOfAProductFromCustomer(@Param("userId") Long userId, @Param("productId") Long prductId) throws Exception {
        return reviewService.getAllReviewOfAProductFromCustomer(userId, prductId);
    }
    @GetMapping("/review/show/detail{reviewId}")
    public Review showReviewDetail(@Param("reviewId") Long reviewId) throws Exception {
        return reviewService.getReviewById(reviewId);
    }
    @PostMapping("/review/add")
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto) throws Exception {
        return reviewService.addReview(reviewDto);
    }
    @DeleteMapping("/review/del/{reviewId}")
    public ResponseEntity<?> deleteReview(@Param("reviewId") Long reviewId) throws Exception {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Review deleted");
    }
    @PutMapping("/review/change")
    public Review changeReview(@RequestBody @Valid ReviewDto reviewDto) throws Exception {
        return reviewService.changeReview(reviewDto);
    }
}
