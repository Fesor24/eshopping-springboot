package com.eshopping.project.service;

import com.eshopping.project.models.requests.category.CategorySearchParams;
import com.eshopping.project.models.requests.category.CreateCategoryRequest;
import com.eshopping.project.models.response.PaginatedList;
import com.eshopping.project.models.response.category.GetCategoryResponse;
import com.eshopping.project.primitives.Result;
import com.eshopping.project.primitives.ResultT;

import java.util.List;

public interface ICategoryService extends IBaseEntityService<GetCategoryResponse, Long>{
    Result add(CreateCategoryRequest request);
    ResultT<GetCategoryResponse> getById(Long id);
    Result delete(Long categoryId);
}
