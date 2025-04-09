package com.eshopping.project.models.response.product;

import com.eshopping.project.entities.Product;
import com.eshopping.project.models.response.BaseResponse;

public class GetProductResponse extends BaseResponse<GetProductResponse> {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
