package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.model.dto.ProductDto;
import com.example.ecommercebackendproject.model.entity.Product;

import java.util.List;

public interface IProductService {
    public List<ProductDto>findAllProducts();
    public ProductDto changeProductInformation(ProductDto productDto);
    public ProductDto addProduct(ProductDto productDto);
    public void deleteProduct(Long id);
    public List<ProductDto>findAllProductBySeller(Long sellerId);
    public List<ProductDto>getAllProductsOfCategory(String category);
    public List<ProductDto>getAllProductsOfBrand(String brand);
    public List<ProductDto>getAllProductsWithPriceInRange(Integer from, Integer To);


}
