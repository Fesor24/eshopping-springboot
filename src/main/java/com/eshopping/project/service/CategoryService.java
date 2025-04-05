package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private List<Category> categories = new ArrayList<Category>();

    @Override
    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public void removeCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(cat -> cat.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        categories.remove(category);
    }
}
