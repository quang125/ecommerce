package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.dto.VoucherDto;
import com.example.ecommercebackendproject.model.entity.Voucher;
import com.example.ecommercebackendproject.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class VoucherController {
    @Autowired
    private IVoucherService voucherService;
    @PostMapping("/voucher/new")
    public Voucher newVoucher(@RequestBody @Valid VoucherDto voucherDto) throws Exception {
        return voucherService.giveVoucherToCustomer(voucherDto);
    }
    @GetMapping("/voucher/find")
    public  Voucher findVoucher(@RequestBody Map<String, Object> jsonRequest) throws Exception {
        String code = (String) jsonRequest.get("code");
        return voucherService.getVoucherByCode(code);
    }
    @GetMapping("/voucher/show")
    public List<Voucher>customerSeeOwnVoucher() throws Exception {
        return voucherService.findAllVoucher();
    }
    @GetMapping("/voucher/show/{userId}")
    public List<Voucher>showVoucherOfCustomer(@Param("userId") Long userId) throws Exception {
        return voucherService.findALlVoucherOfUser(userId);
    }
}
