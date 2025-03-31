package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
