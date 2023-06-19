package com.example.ecommercebackendproject.dao;

import com.example.ecommercebackendproject.model.entity.Role;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {
    @Query("SELECT u FROM Role u WHERE u.role =:role")
    Optional<Role> findByRole(@Param("role") String role);
}
