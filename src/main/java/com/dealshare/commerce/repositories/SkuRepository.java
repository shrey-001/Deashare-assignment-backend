package com.dealshare.commerce.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.dealshare.commerce.entities.Sku;

public interface SkuRepository extends CrudRepository<Sku, Integer>{
    List<Sku> findByProduct_id(Integer keyword);
}
