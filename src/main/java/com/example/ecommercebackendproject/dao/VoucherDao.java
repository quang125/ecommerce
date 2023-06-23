package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherDao extends JpaRepository<Voucher, Long> {
    @Query("SELECT u FROM Voucher u WHERE u.user.id =:userId")
    List<Voucher> findByUserId(@Param("userId") Long userId);
    @Query("SELECT u FROM Voucher u WHERE u.user.id =:userId AND u.code =:code")
    Optional<Voucher> findByCode(@Param("userId") Long userId, @Param("code") String code);
}
