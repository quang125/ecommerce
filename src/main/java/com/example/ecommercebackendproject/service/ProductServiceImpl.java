package com.example.ecommercebackendproject.service;

import com.example.ecommercebackendproject.dao.ProductDao;
import com.example.ecommercebackendproject.dao.UserDao;
import com.example.ecommercebackendproject.model.dto.ProductDto;
import com.example.ecommercebackendproject.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthService authService;
    private ProductDto getProductDto(ProductDto productDto, Product product) {
        product.setBrand(productDto.getBrand());
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setUser(authService.getCurrentLoggedInUser());
        productDao.save(product);
        return productDto;
    }
    private ProductDto setProductDto(ProductDto productDto, Product product){
        productDto.setBrand(product.getBrand());
        productDto.setName(product.getName());
        productDto.setQuantity(product.getQuantity());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        return productDto;
    }
    @Override
    public List<ProductDto> findAllProducts() {
        List<Product>products=productDao.findAll();
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product x:products) productDtos.add(new ProductDto(x.getName(),x.getQuantity(),
                x.getPrice(),x.getCategory(),x.getDescription(),x.getBrand()));
        return productDtos;
    }

    @Override
    public ProductDto changeProductInformation(Long productId, ProductDto productDto) throws Exception {
        try {
            Product product=productDao.findById(productId).get();
            return getProductDto(productDto, product);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) throws Exception {
        try {
            Product product=new Product();
            return getProductDto(productDto, product);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<ProductDto> findAllProductBySeller(Long sellerId) {
        List<Product>products=productDao.findAllBySeller(sellerId);
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product x:products) productDtos.add(setProductDto(new ProductDto(),x));
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProductsOfCategory(String category) {
        List<Product>products=productDao.findByCategory(category);
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product x:products) productDtos.add(setProductDto(new ProductDto(),x));
        return productDtos;

    }

    @Override
    public List<ProductDto> getAllProductsOfBrand(String brand) {
        List<Product>products=productDao.findByBrand(brand);
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product x:products) productDtos.add(setProductDto(new ProductDto(),x));
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProductsWithPriceInRange(Integer from, Integer to) {
        List<Product>products=productDao.findByPrice(from, to);
        List<ProductDto>productDtos=new ArrayList<>();
        for(Product x:products) productDtos.add(setProductDto(new ProductDto(),x));
        return productDtos;
    }
}
