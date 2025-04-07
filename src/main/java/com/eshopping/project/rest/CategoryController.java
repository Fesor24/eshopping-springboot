package com.eshopping.project.rest;

import com.eshopping.project.entities.Category;
import com.eshopping.project.primitives.Result;
import com.eshopping.project.primitives.ResultT;
import com.eshopping.project.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<ResultT<List<Category>>> getCategories(){
        return ResponseEntity.ok(Result.create(
                this.categoryService.getCategories()
        ));
    }

    @PostMapping("/admin/category")
    public ResponseEntity<Result> createCategory(@Valid @RequestBody Category category){
        categoryService.addCategory(category);
        return ResponseEntity.ok(Result.success());
    }

    @DeleteMapping("/admin/category/{categoryId}")
    public ResponseEntity<Result> deleteCategory(@PathVariable Long categoryId){
        categoryService.removeCategory(categoryId);

        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
}
