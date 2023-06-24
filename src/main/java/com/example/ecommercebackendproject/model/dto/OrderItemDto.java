package com.example.ecommercebackendproject.model.dto;

import com.example.ecommercebackendproject.model.entity.Product;
import com.example.ecommercebackendproject.model.entity.PurchaseOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
public class OrderItemDto {
    private Long productId;
    private int quantity;

}
