package com.example.brandstoftracker.api.httpResponse;


import lombok.Data;

@Data
public class SuccessDataResponse<T> extends DataResponse{
    public SuccessDataResponse(String message,T data){
        super(message,true,data);
    }
}
