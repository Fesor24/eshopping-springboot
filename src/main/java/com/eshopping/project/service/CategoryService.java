package com.eshopping.project.service;

import com.eshopping.project.entities.Category;
import com.eshopping.project.models.requests.category.CategorySearchParams;
import com.eshopping.project.models.requests.category.CreateCategoryRequest;
import com.eshopping.project.models.response.PaginatedList;
import com.eshopping.project.models.response.category.GetCategoryResponse;
import com.eshopping.project.primitives.*;
import com.eshopping.project.primitives.Error;
import com.eshopping.project.repositories.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
            return Result.failure(Error.BadRequest("category.exist", "Category with name exist"));
        }

        Category category = new Category(request.getName());

        this.categoryRepository.save(category);

        return Result.success();
    }

    @Override
    public ResultT<PaginatedList<GetCategoryResponse>> search(CategorySearchParams searchParams) {
        Sort sort = searchParams.getSortOrder().equalsIgnoreCase("asc") ?
                Sort.by(searchParams.getSortBy()).ascending() :
                Sort.by(searchParams.getSortBy()).descending();

        Pageable pageDetails = PageRequest.of(
                searchParams.getPageNumber(),
                searchParams.getPageSize(), sort);

        Page<Category> categories = this.categoryRepository.findAll(pageDetails);

        List<GetCategoryResponse> categoriesResponse = categories.getContent()
                .stream().map(category -> modelMapper.map(category, GetCategoryResponse.class))
                .collect(Collectors.toList());

        PaginatedList<GetCategoryResponse> response = new PaginatedList<>();
        response.setContent(categoriesResponse);
        response.setPageNumber(categories.getNumber());
        response.setPageSize(categories.getSize());
        response.setTotalElements(categories.getNumberOfElements());
        response.setTotalPages(categories.getTotalPages());

        return Result.create(response);
    }

    @Override
    public ResultT<GetCategoryResponse> getById(Long id) {
        Optional<Category> category = this.categoryRepository.findById(id);

        ResultT<Category> response = category.map(ResultT::new) // ctor reference...calls the appropriate ctor
                .orElseGet(() -> Result.failure(
                        Error.NotFound("not.found", "Category not found")));

        if(response.getIsSuccess())
            return Result.create(modelMapper.map(response.getBody(), GetCategoryResponse.class));

        return Result.failure(response.getError());
    }

    @Override
    public Result removeCategory(Long categoryId) {
        Optional<Category> category = this.categoryRepository.findById(categoryId);

        if(category.isEmpty()){
            return Result.failure(Error.NotFound(
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
