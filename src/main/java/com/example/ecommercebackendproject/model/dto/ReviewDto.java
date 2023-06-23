package com.example.ecommercebackendproject.model.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long userId;
    private Long productId;
    private String content;
}
