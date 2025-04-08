package com.eshopping.project.shared;

import com.eshopping.project.primitives.Error;

public final class ApiResponse<TBody> extends ApiBaseResponse{
    private final TBody body;

    public ApiResponse(TBody body){
        super();
        this.body = body;
    }

    public ApiResponse(Error error){
        super(error);
        this.body = null;
    }

    public TBody getBody(){
        return this.body;
    }
}
