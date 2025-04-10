package com.eshopping.project.primitives;

import lombok.Data;

@Data
public class Error {
    public static Error None = new Error("", "");

    protected final String code;
    protected final String message;

    public Error(String code, String message){
        this.code = code;
        this.message = message;
    }

    public static NotFoundError NotFound(String code, String message){
        return new NotFoundError(code, message);
    }

    public static BadRequestError BadRequest(String code, String message){
        return new BadRequestError(code, message);
    }
}
