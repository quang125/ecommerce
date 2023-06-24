package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.entity.OrderAddress;

import java.util.List;

public interface IOrderAddressService {
    public List<OrderAddress> showAllOrderAddressOfACustomer(Long userId) throws Exception;
}
