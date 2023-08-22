package com.dealshare.commerce.repositories;

import org.springframework.data.repository.CrudRepository;
import com.dealshare.commerce.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
