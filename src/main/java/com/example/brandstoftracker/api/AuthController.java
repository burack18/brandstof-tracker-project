package com.example.brandstoftracker.api;


import com.example.brandstoftracker.api.dto.LoginRequest;
import com.example.brandstoftracker.api.dto.LoginResponse;
import com.example.brandstoftracker.api.dto.RegisterRequest;
import com.example.brandstoftracker.api.httpResponse.Response;
import com.example.brandstoftracker.api.httpResponse.SuccessDataResponse;
import com.example.brandstoftracker.service.abstracts.AuthService;
import com.example.brandstoftracker.utilities.languageLocalization.MessageCreater;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    private final MessageCreater messageCreater;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest)
    {
        return new ResponseEntity(new SuccessDataResponse(messageCreater.getMessage("login"),this.service.login(loginRequest)),HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request){
        service.Register(request);
        return new ResponseEntity(new Response(messageCreater.getMessage("register")), HttpStatus.CREATED);
    }
}
