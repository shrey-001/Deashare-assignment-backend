package com.dealshare.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealshare.commerce.entities.Product;
import com.dealshare.commerce.services.ProductService;
import java.util.List;


@Controller
@RequestMapping(path = "api/product")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(path="/id")
    @Cacheable(key = "#product_id", value = "Product")
    public @ResponseBody Product getProduct(@RequestParam Integer product_id){
        return productService.getProductById(product_id);
    }
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Product> getAllProduct(){
        return productService.getAllProducts();
    }
    @GetMapping(path="/popular")
    public @ResponseBody List<Product> getPopularProducts(){
        return productService.getPopularProducts();
    }
    @GetMapping(path="/categories")
    public @ResponseBody List<Product> getAllCategory(@RequestParam Integer category_id) {
        return productService.getProductByCategory(category_id);
    }
    @PostMapping(path  = "/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }
    
}
