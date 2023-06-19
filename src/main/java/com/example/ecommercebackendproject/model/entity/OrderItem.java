package com.example.ecommercebackendproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String status;
    @ManyToOne
    @JsonIgnore
    private PurchaseOrder purchaseOrder;
    @OneToOne
    @JsonIgnore
    private Product product;
    private int quantity;
    private String paymentMethod;
}
