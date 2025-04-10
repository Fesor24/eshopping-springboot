package com.eshopping.project.primitives;

import lombok.Data;

import java.util.function.Function;
import java.util.function.Supplier;

@Data
public sealed class Result permits ResultT{
    protected final boolean isSuccess;
    protected boolean isFailure;
    protected final Error error;

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

    public static <TBody> ResultT<TBody> create(TBody body){
        return new ResultT<>(body);
    }

    public static <TBody> ResultT<TBody> failure(Error error){
        return new ResultT<>(error);
    }

    public <TResult> TResult match(Supplier<TResult> success, Function<Error, TResult> failure){
        return this.isSuccess ? success.get() : failure.apply(getError());
    }

    public boolean getIsFailure(){
        return !this.isSuccess;
    }
}

