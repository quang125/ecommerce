package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.OrderItemDao;
import com.example.ecommercebackendproject.dao.ProductDao;
import com.example.ecommercebackendproject.dao.PurchaseOrderDao;
import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.model.entity.OrderItem;
import com.example.ecommercebackendproject.model.entity.Product;
import com.example.ecommercebackendproject.model.entity.PurchaseOrder;
import com.example.ecommercebackendproject.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderItemServiceImpl implements IOrderItemService{
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PurchaseOrderDao purchaseOrderDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public List<OrderItem> showOrderItem(Long orderId) throws Exception {
        try
        {
            User user=getCurrentLoggedInUser();
            PurchaseOrder purchaseOrder=purchaseOrderDao.findById(orderId).get();
            if(purchaseOrder.getUser().getId()!=user.getId()){
                throw new Exception("Access denied");
            }
            return orderItemDao.findByOrderId(orderId);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void cancelOrder(Long orderItemId) throws Exception {
        try
        {
            User user=getCurrentLoggedInUser();
            OrderItem orderItem=orderItemDao.findById(orderItemId).get();
            PurchaseOrder purchaseOrder=orderItem.getPurchaseOrder();
            if(purchaseOrder.getUser().getId()!=user.getId()){
                throw new Exception("Access denied");
            }
            orderItem.setStatus("canceled");
            Product product=orderItem.getProduct();
            purchaseOrder.setTotal(purchaseOrder.getTotal()+product.getPrice()+orderItem.getQuantity());
            product.setQuantity(product.getQuantity()+orderItem.getQuantity());
            orderItemDao.save(orderItem);
            purchaseOrderDao.save(purchaseOrder);
            productDao.save(product);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public List<OrderItem> findOrderItemWithPriceInRange(Long from, Long to) throws Exception {
        try
        {
            User user=getCurrentLoggedInUser();
            List<OrderItem>orderItemList=new ArrayList<>();
            List<PurchaseOrder>purchaseOrders=purchaseOrderDao.findByUserId(user.getId());
            for(PurchaseOrder x:purchaseOrders){
                List<OrderItem>orderItems=orderItemDao.findByRange(x.getId(),from, to);
                for(OrderItem y:orderItems) orderItemList.add(y);
            }
            return orderItemList;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<OrderItem> findOrderItemWithStatus(String status) throws Exception {
        try
        {
            User user=getCurrentLoggedInUser();
            List<OrderItem>orderItemList=new ArrayList<>();
            List<PurchaseOrder>purchaseOrders=purchaseOrderDao.findByUserId(user.getId());
            for(PurchaseOrder x:purchaseOrders){
                List<OrderItem>orderItems=orderItemDao.findByStatus(x.getId(),status);
                for(OrderItem y:orderItems) orderItemList.add(y);
            }
            return orderItemList;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<OrderItem> showAllOrderItem(Long userId) throws Exception {
        try
        {
            List<OrderItem>orderItemList=new ArrayList<>();
            List<PurchaseOrder>purchaseOrders=purchaseOrderDao.findByUserId(userId);
            for(PurchaseOrder x:purchaseOrders){
                List<OrderItem>orderItems=x.getOrderItems();
                for(OrderItem y:orderItems) orderItemList.add(y);
            }
            return orderItemList;
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
