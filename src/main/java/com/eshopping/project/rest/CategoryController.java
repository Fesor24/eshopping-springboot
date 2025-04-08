package com.eshopping.project.rest;

import com.eshopping.project.models.requests.category.CategorySearchParams;
import com.eshopping.project.models.requests.category.CreateCategoryRequest;
import com.eshopping.project.models.response.PaginatedList;
import com.eshopping.project.models.response.category.GetCategoryResponse;
import com.eshopping.project.primitives.ResultT;
import com.eshopping.project.service.ICategoryService;
import com.eshopping.project.shared.ApiBaseResponse;
import com.eshopping.project.shared.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController extends BaseController {
    private ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<ApiResponse<PaginatedList<GetCategoryResponse>>> getCategories(
            @RequestParam(name="pageSize", required = false) Integer pageSize,
            @RequestParam(name="pageNumber", required = false) Integer pageNumber,
            @RequestParam(name= "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(name= "sortOrder", required = false, defaultValue = "asc") String sortOrder
    ){
        CategorySearchParams searchParams = new CategorySearchParams();
        searchParams.setPageSize(pageSize);
        searchParams.setPageNumber(pageNumber);
        searchParams.setSortBy(sortBy);
        searchParams.setSortOrder(sortOrder);

        return ResponseEntity.ok(new ApiResponse<PaginatedList<GetCategoryResponse>>(
                this.categoryService.search(searchParams).getBody()
        ));
    }

    @GetMapping("/public/category/{categoryId}")
    public ResponseEntity<ApiResponse<GetCategoryResponse>> getById(@PathVariable Long categoryId){
        ResultT<GetCategoryResponse> response = this.categoryService.getById(categoryId);

        return response.match(this::handleSuccess, this::handleError);
    }

    @PostMapping("/admin/category")
    public ResponseEntity<ApiBaseResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        var response = categoryService.addCategory(request);

        return response.match(this::handleEmptySuccess, this::handleEmptyError);
    }

    @DeleteMapping("/admin/category/{categoryId}")
    public ResponseEntity<ApiBaseResponse> deleteCategory(@PathVariable Long categoryId){
        var response = categoryService.removeCategory(categoryId);

        return response.match(this::handleEmptySuccess, this::handleEmptyError);
    }
}
