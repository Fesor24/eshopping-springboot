package com.eshopping.project.primitives;

public class BadRequestError extends Error{
    public BadRequestError(String code, String message){
        super(code, message);
    }
}
