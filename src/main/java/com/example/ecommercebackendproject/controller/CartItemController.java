package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.dto.CartItemDto;
import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CartItemController {
    @Autowired
    private ICartItemService cartItemService;
    @PostMapping("/cart/add")
    public List<CartItem> addToCart(@RequestBody @Valid CartItemDto cartItemDto) throws Exception {
        return cartItemService.addItemToCart(cartItemDto);
    }
    @PutMapping("/cart/change")
    public List<CartItem> changeQuantity(@RequestBody @Valid CartItemDto cartItemDto) throws Exception {
        return cartItemService.changeQuantity(cartItemDto);
    }
    @DeleteMapping("/cart/delete/{cartItemId}")
    public List<CartItem> deleteItem(@PathVariable("cartItemId") Long cartItemId) throws Exception {
        return cartItemService.deleteItem(cartItemId);
    }
}
