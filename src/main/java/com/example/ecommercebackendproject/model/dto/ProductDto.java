package com.example.ecommercebackendproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;

    private Integer quantity;

    private Integer price;

    private String category;
    private String description;

    private String brand;
}
