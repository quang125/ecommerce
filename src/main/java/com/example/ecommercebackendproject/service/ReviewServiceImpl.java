package com.example.ecommercebackendproject.service;
import com.example.ecommercebackendproject.dao.ProductDao;
import com.example.ecommercebackendproject.dao.ReviewDao;
import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.model.dto.ReviewDto;
import com.example.ecommercebackendproject.model.entity.Review;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService{
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Review> getAllReviewOfCustomer(Long userId) throws Exception {
        try {
            return reviewDao.findAllByUserId(userId);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Review> getAllReviewOfAProductFromCustomer(Long userId, Long productId) throws Exception {
        try {
            return reviewDao.findReviewOfProductByUser(userId, productId);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        try {
            return reviewDao.findById(reviewId).get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Review addReview(ReviewDto reviewDto) throws Exception {
        try{
            Review review=new Review();
            review.setUser(userDao.findById(reviewDto.getUserId()).get());
            review.setContent(review.getContent());
            review.setProduct(productDao.findById(reviewDto.getProductId()).get());
            return reviewDao.save(review);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void deleteReview(Long reviewId) throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            Review review = reviewDao.findById(reviewId).get();
            if (review.getUser().getId() != user.getId()) {
                throw new Exception("You not have permission to do this action");
            }
            reviewDao.delete(review);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Review changeReview(ReviewDto reviewDto) throws Exception {
        try{
            Review review=new Review();
            review.setUser(userDao.findById(reviewDto.getUserId()).get());
            review.setContent(review.getContent());
            review.setProduct(productDao.findById(reviewDto.getProductId()).get());
            return reviewDao.save(review);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public User getCurrentLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userDao.findByUsername(username).get();
        return user;
    }
}
