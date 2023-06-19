package com.example.ecommercebackendproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table
@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private LocalDate orderDate;
    private Long total;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchase_order_id")
    @JsonIgnore
    private List<OrderItem> orderItems;
}
