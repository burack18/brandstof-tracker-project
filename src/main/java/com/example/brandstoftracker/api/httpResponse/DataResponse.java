package com.example.brandstoftracker.api.httpResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataResponse<T> extends Response {

    private Boolean isSuccess;
    private T data;

    public DataResponse(String message, Boolean b, T data) {
        super(message);
        this.isSuccess=b;
        this.data=data;
    }
}
