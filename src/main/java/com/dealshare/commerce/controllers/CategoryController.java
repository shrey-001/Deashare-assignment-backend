package com.dealshare.commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dealshare.commerce.entities.Category;
import com.dealshare.commerce.services.CategoryService;

@Controller
@RequestMapping(path="api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping(path  = "/add")
    public @ResponseBody Category addNewCategory (@RequestParam String name) {
        return categoryService.createNewCategory(name);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }    
}
