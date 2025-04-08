package com.eshopping.project.rest;

import com.eshopping.project.primitives.BadRequestError;
import com.eshopping.project.primitives.Error;
import com.eshopping.project.primitives.NotFoundError;
import com.eshopping.project.shared.ApiBaseResponse;
import com.eshopping.project.shared.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


public class BaseController {
    protected <TBody> ResponseEntity<ApiResponse<TBody>> handleError(Error error){
        if(error instanceof NotFoundError notFoundError){
            return new ResponseEntity<>(
                    new ApiResponse<>(error), HttpStatus.NOT_FOUND);
        }
        else if(error instanceof BadRequestError badRequestError){
            return new ResponseEntity<>(
                    new ApiResponse<>(error), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(
                    new ApiResponse<>(error), HttpStatus.BAD_REQUEST);
        }
    }

    protected ResponseEntity<ApiBaseResponse> handleEmptyError(Error error){
        if(error instanceof NotFoundError notFoundError){
            return new ResponseEntity<>(
                    new ApiBaseResponse(error), HttpStatus.NOT_FOUND);
        }
        else if(error instanceof BadRequestError badRequestError){
            return new ResponseEntity<>(
                    new ApiBaseResponse(error), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(
                    new ApiBaseResponse(error), HttpStatus.BAD_REQUEST);
        }
    }

    protected <TBody> ResponseEntity<ApiResponse<TBody>> handleSuccess(TBody body){
        return new ResponseEntity<>(new ApiResponse<>(body), HttpStatus.OK);
    }

    protected ResponseEntity<ApiBaseResponse> handleEmptySuccess(){
        return new ResponseEntity<>(new ApiBaseResponse(), HttpStatus.OK);
    }
}
