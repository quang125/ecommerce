package com.example.ecommercebackendproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Table
@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDateTime orderDate;
    private Long total;
    private String paymentMethod;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_order_id")
    @JsonIgnore
    private List<OrderItem> orderItems;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_order_id")
    @JsonIgnore
    private List<OrderAddress> orderAddress;
    @ManyToOne
    @JsonIgnore
    private User user;
}
