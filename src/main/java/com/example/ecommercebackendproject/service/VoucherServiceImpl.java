package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.dao.VoucherDao;
import com.example.ecommercebackendproject.model.dto.VoucherDto;
import com.example.ecommercebackendproject.model.entity.User;
import com.example.ecommercebackendproject.model.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoucherServiceImpl implements IVoucherService{
    @Autowired
    private VoucherDao voucherDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<Voucher> findALlVoucherOfUser() throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            return voucherDao.findByUserId(user.getId());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Voucher getVoucherByCode(String code) throws Exception {
        try {
            User user = getCurrentLoggedInUser();
            return voucherDao.findByCode(user.getId(), code).get();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Voucher giveVoucherToCustomer(VoucherDto voucherDto) {
        Voucher voucher=new Voucher();
        voucher.setCode(voucherDto.getCode());
        voucher.setDiscount(voucher.getDiscount());
        voucher.setUser(userDao.findById(voucherDto.getUserId()).get());
        voucher.setEndDate(LocalDateTime.now().plusDays(voucherDto.getExpireTime()));
        return voucherDao.save(voucher);
    }
    public User getCurrentLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User user=userDao.findByUsername(username).get();
        return user;
    }
}
