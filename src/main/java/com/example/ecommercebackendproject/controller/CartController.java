package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.service.CartServiceImpl;
import com.example.ecommercebackendproject.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private ICartService cartService;
    @GetMapping("/cart/show")
    public List<CartItem>showCustomerCart() throws Exception {
        return cartService.getCart();
    }
    @DeleteMapping("/cart/clean")
    public ResponseEntity<?>deleteCart() throws Exception {
        cartService.deleteCart();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Clean cart complete");
    }
}
