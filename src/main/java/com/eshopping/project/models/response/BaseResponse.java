package com.eshopping.project.models.response;

import com.eshopping.project.primitives.ResultT;
import lombok.Data;

@SuppressWarnings("unchecked")
public abstract class BaseResponse<TBody extends BaseResponse> {
    public ResultT<TBody> toResult(){
        return new ResultT<TBody>((TBody)this);
    }
}
