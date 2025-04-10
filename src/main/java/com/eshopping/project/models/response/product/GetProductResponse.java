package com.eshopping.project.models.response.product;

import com.eshopping.project.entities.Product;
import com.eshopping.project.models.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GetProductResponse extends BaseResponse<GetProductResponse> {
    private String name;
    private String description;
}
