package com.example.ecommercebackendproject.controller;

import com.example.ecommercebackendproject.model.dto.ProductDto;
import com.example.ecommercebackendproject.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/products")
    public List<ProductDto> findALL(){
        return productService.findAllProducts();
    }
    @PutMapping("products/{productId}/edit")
    public ProductDto changeInfoProduct(@PathVariable("productId") Long productId, @RequestBody @Valid ProductDto productDto) throws Exception {
        return productService.changeProductInformation(productId,productDto);
    }
    @PostMapping("/products/new")
    public ProductDto saveProduct(@RequestBody @Valid ProductDto productDto) throws Exception {
        return productService.addProduct(productDto);
    }
    @GetMapping("/products/show/seller/{sellerId}")
    public List<ProductDto> getProductsBySeller(@PathVariable("sellerId") Long sellerId){
        return productService.findAllProductBySeller(sellerId);
    }
    @GetMapping("/products/show/brand/{brand}")
    public List<ProductDto> getProductsByBrand(@PathVariable("brand") String brand){
        return productService.getAllProductsOfBrand(brand);
    }
    @GetMapping("/products/show/category/{category}")
    public List<ProductDto> getProductsByCategory(@PathVariable("category") String category){
        return productService.getAllProductsOfCategory(category);
    }
    @GetMapping("products/show/price/{from}/{to}")
    public List<ProductDto> getProductsByPrice(@PathVariable("from") Integer from, @PathVariable("to") Integer to){
        return productService.getAllProductsWithPriceInRange(from, to);
    }
}
