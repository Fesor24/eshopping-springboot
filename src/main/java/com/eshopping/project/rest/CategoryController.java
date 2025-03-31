package com.eshopping.project.rest;

import com.eshopping.project.entities.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private List<Category> categories = new ArrayList<Category>();

    @GetMapping("/api/public/categories")
    public List<Category> getCategories(){
        return categories;
    }
}
