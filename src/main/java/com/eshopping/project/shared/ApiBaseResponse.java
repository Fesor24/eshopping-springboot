package com.eshopping.project.shared;

import com.eshopping.project.primitives.Error;

public sealed class ApiBaseResponse permits ApiResponse {
    protected  boolean success;
    protected final Error error;

    public ApiBaseResponse(){
        this.success = true;
        this.error = Error.None;
    }

    public ApiBaseResponse(Error error) {
        this.success = false;
        this.error = error;
    }

    public Error getError() {
        return this.error;
    }

    public boolean getSuccess(){
        return this.success;
    }
}
