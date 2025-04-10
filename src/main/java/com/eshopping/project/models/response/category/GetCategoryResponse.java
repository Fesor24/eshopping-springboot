package com.eshopping.project.models.response.category;

import com.eshopping.project.models.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public final class GetCategoryResponse extends BaseResponse<GetCategoryResponse> {
    Long id;
    String name;
}
