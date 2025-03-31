package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {
    void addCategory(Category category);
    List<Category> getCategories();
}
