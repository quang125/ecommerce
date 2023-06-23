package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    public List<OrderItem>showOrderItem();
    public List<OrderItem> checkout(List<CartItem> cartItemList);
    public void cancelOrder(Long orderItemId);
}
