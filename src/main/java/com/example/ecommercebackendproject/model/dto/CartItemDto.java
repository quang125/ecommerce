package com.example.ecommercebackendproject.model.dto;

import lombok.Data;

@Data
public class CartItemDto {
    public Long productId;
    public Integer quantity;
}
