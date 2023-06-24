package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderAddressDao extends JpaRepository<OrderAddress,Long> {
}
