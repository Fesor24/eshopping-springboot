package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import com.eshopping.project.primitives.Result;
import com.eshopping.project.primitives.ResultT;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {
    Result addCategory(Category category);
    ResultT<List<Category>> getCategories();
    ResultT<Category> getById(Long id);
    Result removeCategory(Long categoryId);
}
