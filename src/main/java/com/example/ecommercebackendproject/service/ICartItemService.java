package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.dto.CartItemDto;
import com.example.ecommercebackendproject.model.entity.CartItem;

import java.util.List;

public interface ICartItemService {
    public List<CartItem> addItemToCart(CartItemDto cartItemDto) throws Exception;
    public List<CartItem> changeQuantity(CartItemDto cartItemDto) throws Exception;
    public List<CartItem> deleteItem(Long cartItemId) throws Exception;
}
