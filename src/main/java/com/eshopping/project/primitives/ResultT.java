package com.eshopping.project.primitives;

import java.util.function.Function;

public final class ResultT<TBody> extends Result {
    private final TBody body;

    public ResultT(TBody body){
        super();
        this.body = body;
    }

    public ResultT(Error error){
        super(error);
        this.body = null;
    }

    public <TResult> TResult match(Function<TBody, TResult> success, Function<Error, TResult> failure){
        return this.getIsSuccess() ? success.apply(this.getBody()) : failure.apply(getError());
    }

    public TBody getBody() {
        if(getIsSuccess()){
            return this.body;
        }else{
            throw new RuntimeException("Value cannot be accessed!");
        }
    }
}
