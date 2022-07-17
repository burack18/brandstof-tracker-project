package com.example.brandstoftracker.api.httpResponse;

import lombok.Data;

@Data
public class ErrorResponse extends Response{
    private Boolean isSuccess;
    public ErrorResponse(String message){
        super(message);
        this.isSuccess=false;

    }
}
