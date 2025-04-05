package com.eshopping.project.rest;

import com.eshopping.project.entities.Category;
import com.eshopping.project.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("/api/admin/category")
    public String createCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return "Category created successfully";
    }

    @DeleteMapping("/api/admin/category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
            categoryService.removeCategory(categoryId);

            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        }
        catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
