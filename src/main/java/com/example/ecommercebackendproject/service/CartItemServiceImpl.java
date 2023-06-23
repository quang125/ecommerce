package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.CartDao;
import com.example.ecommercebackendproject.dao.CartItemDao;
import com.example.ecommercebackendproject.dao.ProductDao;
import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.model.dto.CartItemDto;
import com.example.ecommercebackendproject.model.entity.Cart;
import com.example.ecommercebackendproject.model.entity.CartItem;
import com.example.ecommercebackendproject.model.entity.Product;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements ICartItemService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartItemDao cartItemDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public List<CartItem> addItemToCart(CartItemDto cartItemDto) throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            Cart cart = cartDao.findCartByUserId(user.getId());
            Optional<CartItem> item = cartItemDao.findByProductIDAndCartId(cart.getId(), cartItemDto.getProductId());
            Product product = productDao.findById(cartItemDto.productId).get();
            boolean can=false;
            if (item.isPresent()) {
                CartItem cartItem = item.get();
                if(cartItem.getQuantity() + cartItemDto.getQuantity()<=product.getQuantity()) {
                    cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
                    cartItemDao.save(cartItem);
                    can=true;
                }
            } else {
                if(product.getQuantity()>=cartItemDto.getQuantity()) {
                    CartItem cartItem = new CartItem();
                    cartItem.setCart(cart);
                    cartItem.setProduct(product);
                    cartItem.setQuantity(cartItemDto.getQuantity());
                    cartItemDao.save(cartItem);
                    can=true;
                }
            }
            if(can){
                cart.setCartTotal(cart.getCartTotal() + cartItemDto.getQuantity() * product.getPrice());
                cartDao.save(cart);
            }
            else throw new Exception("Not enough product");
            List<CartItem> cartItemList = cartItemDao.findByCartId(cart.getId());
            return cartItemList;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<CartItem> changeQuantity(CartItemDto cartItemDto) throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            Cart cart = cartDao.findCartByUserId(user.getId());
            Optional<CartItem> item = cartItemDao.findByProductIDAndCartId(cart.getId(), cartItemDto.getProductId());
            Product product = productDao.findById(cartItemDto.productId).get();
            CartItem cartItem = item.get();
            if(product.getQuantity()>=cartItemDto.getQuantity()) {
                cart.setCartTotal(cart.getCartTotal()-cartItem.getQuantity()*product.getPrice());
                cartItem.setQuantity(cartItemDto.getQuantity());
                cart.setCartTotal(cart.getCartTotal()+cartItemDto.getQuantity()* product.getPrice());
                cartItemDao.save(cartItem);
                cartDao.save(cart);
            }
            return cartItemDao.findByCartId(cart.getId());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<CartItem> deleteItem(Long cartItemId) throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            Cart cart = cartDao.findCartByUserId(user.getId());
            Optional<CartItem> item = cartItemDao.findById(cartItemId);
            CartItem cartItem = item.get();
            Product product=cartItem.getProduct();
            cart.setCartTotal(cart.getCartTotal()-cartItem.getQuantity()*product.getPrice());
            cartDao.save(cart);
            cartItemDao.delete(cartItem);
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
