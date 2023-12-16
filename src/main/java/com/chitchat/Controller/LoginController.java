package com.chitchat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chitchat.Services.LoginRegisterService;
import com.chitchat.ViewModels.LoginRequest;
import com.chitchat.ViewModels.LoginResponse;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginRegisterService _loginRegisterService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@RequestBody LoginRequest login){
        if(login==null){
          LoginResponse loginResponse = new LoginResponse();
          loginResponse.ErrorMessage= "Please enter the valid input";
          return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        LoginResponse loginResponse = _loginRegisterService.LoginUser(login);
        if(loginResponse.ErrorMessage == ""){
            return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
    } 
}
