package com.example.ecommercebackendproject.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class CheckoutDto {
    private List<OrderItemDto> orderItemDtoList;
    private Integer discount;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String ward;
    @Column(nullable = false)
    private String address;
    private String paymentMethod;
}
