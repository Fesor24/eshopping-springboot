package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import com.eshopping.project.exceptions.ApiException;
import com.eshopping.project.exceptions.ResourceNotFoundException;
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
    public void addCategory(Category category) {
        Optional<Category> savedCategory = this.categoryRepository
                .findByName(category.getName());

        if(savedCategory.isPresent()) {
            throw new ApiException("Category with name " + category.getName() + " already exists!!!");
        }
        this.categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void removeCategory(Long categoryId) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);

        if(category.isEmpty()){
            throw new ResourceNotFoundException("Category", "categoryId", categoryId);
        }

        this.categoryRepository.delete(category.get());


//        Category category = categories.stream()
//                .filter(cat -> cat.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }
}
