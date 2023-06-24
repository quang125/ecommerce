package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.model.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {
    public List<OrderItem>showOrderItem(Long orderId) throws Exception;
    public void cancelOrder(Long orderItemId) throws Exception;
    public List<OrderItem> findOrderItemWithPriceInRange(Long from, Long to) throws Exception;
    public List<OrderItem> findOrderItemWithStatus(String status) throws Exception;
    public List<OrderItem> showAllOrderItem(Long userId) throws Exception;
}
