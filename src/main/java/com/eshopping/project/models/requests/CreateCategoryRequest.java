package com.eshopping.project.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class CreateCategoryRequest {
    @NotNull(message = "Name can not be null")
    @NotBlank(message = "Name can not be blank")
    String name;

    public String getName(){
        return this.name;
    }
}
