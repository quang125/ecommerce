package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.dto.VoucherDto;
import com.example.ecommercebackendproject.model.entity.Voucher;

import java.util.List;

public interface IVoucherService {
    public List<Voucher> findALlVoucherOfUser(Long userId) throws Exception;
    public List<Voucher> findAllVoucher() throws Exception;
    public Voucher getVoucherByCode(String code) throws Exception;
    public Voucher giveVoucherToCustomer(VoucherDto voucherDto) throws Exception;
}
