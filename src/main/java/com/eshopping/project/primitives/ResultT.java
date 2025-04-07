package com.eshopping.project.primitives;

public final class ResultT<TBody> extends Result {
    private TBody body;

    public ResultT(TBody body){
        super();
        this.body = body;
    }

    public TBody getBody() {
        if(getIsSuccess()){
            return this.body;
        }else{
            throw new RuntimeException("Value can not be accessed!");
        }
    }
}
