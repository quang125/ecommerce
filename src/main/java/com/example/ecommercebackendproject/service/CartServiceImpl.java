package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.CartDao;
import com.example.ecommercebackendproject.dao.CartItemDao;
import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.model.entity.Cart;
import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;

    @Override
    public void deleteCart() throws Exception {
        try{
            User user = getCurrentLoggedInUser();
            Cart cart = cartDao.findCartByUserId(user.getId());
            List<CartItem>cartItemList=cartItemDao.findByCartId(cart.getId());
            for(CartItem x:cartItemList) cartItemDao.delete(x);
            cart.setCartTotal(0.0);
            cartDao.save(cart);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<CartItem> getCart() throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            Cart cart = cartDao.findCartByUserId(user.getId());
            return cartItemDao.findByCartId(cart.getId());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public User getCurrentLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userDao.findByUsername(username).get();
        return user;
    }
}
