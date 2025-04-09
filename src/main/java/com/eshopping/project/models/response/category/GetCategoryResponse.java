package com.eshopping.project.models.response.category;

import com.eshopping.project.models.response.BaseResponse;

public final class GetCategoryResponse extends BaseResponse<GetCategoryResponse> {
    Long id;
    String name;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
