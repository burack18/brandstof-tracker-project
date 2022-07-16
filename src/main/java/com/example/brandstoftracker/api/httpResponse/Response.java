package com.example.brandstoftracker.api.httpResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
    private String message;
    public Response(String message) {
        this.message=message;
    }
}
