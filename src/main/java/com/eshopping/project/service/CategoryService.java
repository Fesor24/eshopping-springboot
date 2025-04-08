package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import com.eshopping.project.exceptions.ApiException;
import com.eshopping.project.exceptions.ResourceNotFoundException;
import com.eshopping.project.models.requests.CreateCategoryRequest;
import com.eshopping.project.models.response.GetCategoryResponse;
import com.eshopping.project.primitives.*;
import com.eshopping.project.primitives.Error;
import com.eshopping.project.repositories.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    //private List<Category> categories = new ArrayList<Category>();

    private ICategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result addCategory(CreateCategoryRequest request) {
        Optional<Category> savedCategory = this.categoryRepository
                .findByName(request.getName());

        if(savedCategory.isPresent()) {
            return Result.failure(new BadRequestError("category.exist", "Category with name exist"));
        }

        Category category = new Category(request.getName());

        this.categoryRepository.save(category);

        return Result.success();
    }

    @Override
    public ResultT<List<GetCategoryResponse>> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        List<GetCategoryResponse> categoriesResponse = categories
                .stream().map(category -> modelMapper.map(category, GetCategoryResponse.class))
                .collect(Collectors.toList());

        return Result.create(categoriesResponse);
    }

    @Override
    public ResultT<GetCategoryResponse> getById(Long id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        ResultT<Category> response = category.map(ResultT::new) // ctor reference...calls the appropriate ctor
                .orElseGet(() -> Result.failure(
                        new Error("not.found", "Category not found")));

        if(response.getIsSuccess())
            return Result.create(modelMapper.map(response.getBody(), GetCategoryResponse.class));

        return Result.failure(response.getError());
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
