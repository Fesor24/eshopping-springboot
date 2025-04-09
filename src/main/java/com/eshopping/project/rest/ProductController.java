package com.eshopping.project.rest;

import com.eshopping.project.models.response.product.GetProductResponse;
import com.eshopping.project.repositories.IProductRepository;
import com.eshopping.project.service.IProductService;
import com.eshopping.project.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<GetProductResponse>> getById(@PathVariable Long productId) {
        var result = productService.getById(productId);

        return result.match(this::handleSuccess, this::handleError);
    }
}
