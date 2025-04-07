package com.eshopping.project.primitives;

public class Error {
    public static Error None = new Error("", "");

    private String code;
    private String message;

    public Error(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
