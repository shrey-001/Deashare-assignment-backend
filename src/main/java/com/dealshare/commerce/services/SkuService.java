package com.dealshare.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealshare.commerce.entities.Sku;
import com.dealshare.commerce.repositories.SkuRepository;

@Service
public class SkuService {
    private final SkuRepository skuRepository;

    @Autowired
    public SkuService(SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }
    public Iterable<Sku> getAllSkuByProduct(Integer product_id){
        return skuRepository.findByProduct_id(product_id);
    }
    public Sku createSku(Sku sku){
        return skuRepository.save(sku);
    }
    public Iterable<Sku> getAllSku(){
        return skuRepository.findAll();
    }
    public Sku getSkuById(Integer id){
        return skuRepository.findById(id).orElse(null);
    }
}
