package com.dealshare.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealshare.commerce.entities.Sku;
import com.dealshare.commerce.services.SkuService;

@Controller
@RequestMapping(path = "api/sku")
public class SkuController {
    
    private final SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @GetMapping("/id")
    public @ResponseBody Sku getSkuById(@RequestParam Integer sku_id){
        return skuService.getSkuById(sku_id);
    }
    @GetMapping("/all")
    public @ResponseBody Iterable<Sku> getAllSku(){
        return skuService.getAllSku();
    }

    @GetMapping("/products")
    public @ResponseBody Iterable<Sku> getAllProduct(@RequestParam Integer product_id) {
        return skuService.getAllSkuByProduct(product_id);
    }

    @PostMapping("/create")
    public @ResponseBody Sku createSku(@RequestBody Sku sku) {
        return skuService.createSku(sku);
    }

}
