package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import com.eshopping.project.exceptions.ApiException;
import com.eshopping.project.exceptions.ResourceNotFoundException;
import com.eshopping.project.primitives.*;
import com.eshopping.project.primitives.Error;
import com.eshopping.project.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    //private List<Category> categories = new ArrayList<Category>();

    private ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Result addCategory(Category category) {
        Optional<Category> savedCategory = this.categoryRepository
                .findByName(category.getName());

        if(savedCategory.isPresent()) {
            return Result.failure(new BadRequestError("category.exist", "Category with name exist"));
        }
        this.categoryRepository.save(category);

        return Result.success();
    }

    @Override
    public ResultT<List<Category>> getCategories() {
        return Result.create(this.categoryRepository.findAll());
    }

    @Override
    public ResultT<Category> getById(Long id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        return category.map(ResultT::new) // ctor reference...calls the appropriate ctor
                .orElseGet(() -> Result.failure(
                        new Error("not.found", "Category not found")));

    }

    @Override
    public Result removeCategory(Long categoryId) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);

        if(category.isEmpty()){
            return Result.failure(new NotFoundError(
                    "not.found", "Category not found"));
        }

        this.categoryRepository.delete(category.get());

        return Result.success();


//        Category category = categories.stream()
//                .filter(cat -> cat.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }
}
