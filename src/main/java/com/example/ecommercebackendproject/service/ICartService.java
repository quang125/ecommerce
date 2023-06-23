package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.entity.CartItem;

import java.util.List;

public interface ICartService {

    public void deleteCart() throws Exception;

    public List<CartItem> getCart() throws Exception;
}
