package com.example.ecommercebackendproject.model.dto;

import lombok.Data;

@Data
public class VoucherDto {
    private Long userId;
    private String code;
    private long expireTime;
    private Integer discount;
}
