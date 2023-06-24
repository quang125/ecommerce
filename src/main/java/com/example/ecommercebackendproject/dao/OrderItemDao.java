package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem,Long> {
    @Query("SELECT u FROM OrderItem u WHERE u.purchaseOrder.id =:orderId")
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
    @Query("SELECT u FROM OrderItem u WHERE u.purchaseOrder.id =:orderId AND (u.product.price*u.quantity BETWEEN :from AND :to)")
    List<OrderItem> findByRange(@Param("orderId") Long orderId,@Param("from") Long from,@Param("to") Long to);
    @Query("SELECT u FROM OrderItem u WHERE u.purchaseOrder.id =:orderId and u.status=: status")
    List<OrderItem> findByStatus(@Param("orderId") Long orderId, @Param("status") String status);
}
