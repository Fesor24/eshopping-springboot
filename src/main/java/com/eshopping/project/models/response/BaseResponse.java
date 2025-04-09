package com.eshopping.project.models.response;

import com.eshopping.project.primitives.ResultT;

@SuppressWarnings("unchecked")
public abstract class BaseResponse<TBody> {
    public ResultT<TBody> toResult(){
        return new ResultT<TBody>((TBody)this);
    }
}
