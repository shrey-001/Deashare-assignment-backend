package com.dealshare.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealshare.commerce.entities.Category;
import com.dealshare.commerce.repositories.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category createNewCategory(String name){
        Category n = new Category();
        n.setName(name);
        categoryRepository.save(n);
        return n;
    }
    public Iterable<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
