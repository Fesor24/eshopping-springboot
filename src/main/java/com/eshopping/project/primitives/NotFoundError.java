package com.eshopping.project.primitives;

public class NotFoundError extends Error{
    public NotFoundError(String code, String message){
        super(code, message);
    }
}
