package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import com.eshopping.project.models.requests.CreateCategoryRequest;
import com.eshopping.project.models.response.GetCategoryResponse;
import com.eshopping.project.primitives.Result;
import com.eshopping.project.primitives.ResultT;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {
    Result addCategory(CreateCategoryRequest request);
    ResultT<List<GetCategoryResponse>> getCategories();
    ResultT<GetCategoryResponse> getById(Long id);
    Result removeCategory(Long categoryId);
}
