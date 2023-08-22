package com.dealshare.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealshare.commerce.entities.Product;
import com.dealshare.commerce.repositories.ProductRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    

    public Product createProduct(Product product) {
        Product p = productRepository.save(product);
        try (JedisPool pool = new JedisPool("localhost", 6379)) {
            try (Jedis jedis = pool.getResource()) {
                String key = Integer.toString(p.getId());
                String key2 = "popular_products";
                jedis.zadd(key2, 0, key);
            }
        }
        return p;
    }
    public List<Product> getProductByCategory(Integer category_id){
        return productRepository.findByCategory_id(category_id);
    }

    public Product getProductById(Integer id){
        
        try (JedisPool pool = new JedisPool("localhost", 6379)) {
            try (Jedis jedis = pool.getResource()) {
                String key = Integer.toString(id);
                String key2 = "popular_products";
                jedis.zincrby(key2, 1 , key);
            }
        }
        Product product = productRepository.findById(id).orElse(null);
        return product;
    }
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getProductsByIds(Set<String> productIds){
        List<Integer> integerProductIds = productIds.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return productRepository.findByIdIn(integerProductIds);
    }
    public List<Product> getPopularProducts(){
        try (JedisPool pool = new JedisPool("localhost", 6379)) {
            try (Jedis jedis = pool.getResource()) {
                String key2 = "popular_products";
                Set<String> products_ids = jedis.zrevrange(key2, 0, 1);
                List<Product> popularProducts = getProductsByIds(products_ids);
                return popularProducts;
            }
        }
    }
    
}
