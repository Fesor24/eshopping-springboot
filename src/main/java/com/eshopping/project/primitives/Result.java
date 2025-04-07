package com.eshopping.project.primitives;

public sealed class Result permits ResultT{
    private boolean isSuccess;
    private boolean isFailure;
    private Error error;

    public Result(){
        isSuccess = true;
        this.error = Error.None;
    }

    public Result(Error error){
        this.isSuccess = false;
        this.error = error;
    }

    public static Result success(){
        return new Result();
    }

    public static Result failure(Error error){
        return new Result(error);
    }

    public static <TBody> ResultT<TBody> create(TBody body){
        return new ResultT<>(body);
    }

    public Error getError(){
        return this.error;
    }

    public boolean getIsSuccess(){
        return this.isSuccess;
    }

    public boolean getIsFailure(){
        return !this.isSuccess;
    }
}

