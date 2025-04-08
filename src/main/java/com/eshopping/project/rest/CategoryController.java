package com.eshopping.project.rest;

import com.eshopping.project.entities.Category;
import com.eshopping.project.models.requests.CreateCategoryRequest;
import com.eshopping.project.models.response.GetCategoryResponse;
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
    public ResponseEntity<ApiResponse<List<GetCategoryResponse>>> getCategories(){
        return ResponseEntity.ok(new ApiResponse<List<GetCategoryResponse>>(
                this.categoryService.getCategories().getBody()
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
