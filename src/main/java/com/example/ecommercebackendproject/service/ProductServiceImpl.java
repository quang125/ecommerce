package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.ProductDao;
import com.example.ecommercebackendproject.model.dto.ProductDto;
import com.example.ecommercebackendproject.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public List<ProductDto> findAllProducts() {
        List<Product>products=productDao.findAll();
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product x:products) productDtos.add(new ProductDto(x.getId(),x.getName(),x.getQuantity(),
                x.getPrice(),x.getCategory(),x.getDescription(),x.getBrand()));
        return productDtos;
    }

    @Override
    public ProductDto changeProductInformation(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<ProductDto> findAllProductBySeller(Long sellerId) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsOfCategory(String category) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsOfBrand(String brand) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsWithPriceInRange(Integer from, Integer To) {
        return null;
    }
}
