package com.example.ecommercebackendproject.service;


import com.example.ecommercebackendproject.model.dto.CheckoutDto;
import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.model.entity.OrderItem;
import com.example.ecommercebackendproject.model.entity.PurchaseOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {
    public PurchaseOrder checkout(CheckoutDto checkoutDto);
    public List<PurchaseOrder>showAllOrder(Long userId) throws Exception;
    public List<PurchaseOrder> findAllOrderWithTypeOfPayment(String paymentMethod) throws Exception;
    public Long showTotalMoneyCustomerOrdered() throws Exception;
    public List<PurchaseOrder> findAllOrderByDate(LocalDateTime from, LocalDateTime to) throws Exception;

}
