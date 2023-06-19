package com.example.ecommercebackendproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class OrderAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String ward;
    @Column(nullable = false)
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderItem orderItem;
}
