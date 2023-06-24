package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.OrderAddressDao;
import com.example.ecommercebackendproject.dao.PurchaseOrderDao;
import com.example.ecommercebackendproject.model.entity.OrderAddress;
import com.example.ecommercebackendproject.model.entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderAddressServiceImpl implements IOrderAddressService{
    @Autowired
    private PurchaseOrderDao purchaseOrderDao;
    @Autowired
    private OrderAddressDao orderAddressDao;
    @Override
    public List<OrderAddress> showAllOrderAddressOfACustomer(Long userId) throws Exception {
        try{
            List<PurchaseOrder>purchaseOrders=purchaseOrderDao.findByUserId(userId);
            List<OrderAddress>orderAddresses=new ArrayList<>();
            for(PurchaseOrder x:purchaseOrders){
                List<OrderAddress>orderAddressList=x.getOrderAddress();
                for(OrderAddress y:orderAddressList) orderAddresses.add(y);
            }
            return orderAddresses;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
