package com.dealshare.commerce.repositories;
import org.springframework.data.repository.CrudRepository;
import com.dealshare.commerce.entities.Product;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByCategory_id(Integer keyword);
    List<Product> findByIdIn(List<Integer> productIds);
}