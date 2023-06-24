package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.*;
import com.example.ecommercebackendproject.model.dto.CheckoutDto;
import com.example.ecommercebackendproject.model.dto.OrderItemDto;
import com.example.ecommercebackendproject.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private PurchaseOrderDao purchaseOrderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderAddressDao orderAddressDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public PurchaseOrder checkout(CheckoutDto checkoutDto) {
        User user=getCurrentLoggedInUser();
        PurchaseOrder purchaseOrder=new PurchaseOrder();
        purchaseOrder.setUser(user);
        List<OrderItemDto>orderItemDtoList=checkoutDto.getOrderItemDtoList();
        Long total=0L;
        for(OrderItemDto x:orderItemDtoList){
            Product product=productDao.findById(x.getProductId()).get();
            total+=product.getPrice()*x.getQuantity();
        }
        purchaseOrder.setTotal(total);
        purchaseOrder.setOrderDate(LocalDateTime.now());
        purchaseOrderDao.save(purchaseOrder);
        for(OrderItemDto x:orderItemDtoList){
            OrderItem orderItem=new OrderItem();
            orderItem.setStatus("processing");
            orderItem.setProduct(productDao.findById(x.getProductId()).get());
            orderItem.setQuantity(x.getQuantity());
            orderItem.setPurchaseOrder(purchaseOrder);
            orderItemDao.save(orderItem);
        }
        OrderAddress orderAddress=new OrderAddress();
        orderAddress.setAddress(checkoutDto.getAddress());
        orderAddress.setCity(checkoutDto.getCity());
        orderAddress.setWard(checkoutDto.getWard());
        orderAddress.setDistrict(checkoutDto.getDistrict());
        orderAddress.setOrder(purchaseOrder);
        orderAddressDao.save(orderAddress);
        return purchaseOrder;
    }

    @Override
    public List<PurchaseOrder> showAllOrder(Long userId) throws Exception {
        try{
            return purchaseOrderDao.findByUserId(userId);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PurchaseOrder> findAllOrderWithTypeOfPayment(String paymentMethod) throws Exception {
        try{
            User user=getCurrentLoggedInUser();
            return purchaseOrderDao.findByPaymentMethod(user.getId(),paymentMethod);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Long showTotalMoneyCustomerOrdered() throws Exception {
        try{
            User user=getCurrentLoggedInUser();
            List<PurchaseOrder>purchaseOrders=purchaseOrderDao.findByUserId(user.getId());
            Long res=0L;
            for(PurchaseOrder x:purchaseOrders) res+=x.getTotal();
            return res;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PurchaseOrder> findAllOrderByDate(LocalDateTime from, LocalDateTime to) throws Exception {
        try{
            User user=getCurrentLoggedInUser();
            return purchaseOrderDao.findByDate(user.getId(),from, to);
        }catch (Exception e){
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
