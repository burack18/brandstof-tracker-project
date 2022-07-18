package com.example.brandstoftracker.api.httpResponse;

public class ErrorDataResponse<T> extends DataResponse<T>{
    public ErrorDataResponse(String message,T data){
        super(message,false,data);
    }
}