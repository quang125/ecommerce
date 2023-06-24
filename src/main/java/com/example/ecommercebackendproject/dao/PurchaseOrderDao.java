package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseOrderDao extends JpaRepository<PurchaseOrder,Long> {
    @Query("SELECT u FROM PurchaseOrder u WHERE u.user.id =:userId")
    List<PurchaseOrder> findByUserId(@Param("userId") Long userId);

    @Query("SELECT u FROM PurchaseOrder u WHERE u.user.id =:userId AND u.paymentMethod=: paymentMethod")
    List<PurchaseOrder> findByPaymentMethod(@Param("userId") Long userId, @Param("paymentMethod") String paymentMethod);
    @Query("SELECT u FROM PurchaseOrder u WHERE u.user.id =:userId AND (u.orderDate BETWEEN :from AND :to)")
    List<PurchaseOrder> findByDate(@Param("userId") Long userId,@Param("from") LocalDateTime from,@Param("to") LocalDateTime to);
}
