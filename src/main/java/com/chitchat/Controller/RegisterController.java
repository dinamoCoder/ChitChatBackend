package com.chitchat.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chitchat.Models.User;
import com.chitchat.Services.LoginRegisterService;
import com.chitchat.ViewModels.RegisterRequest;
import com.chitchat.ViewModels.RegisterResponse;

@RestController
public class RegisterController {

    @Autowired
    private LoginRegisterService _loginRegisterService;
    private final ModelMapper _modelMapper;

    public RegisterController(ModelMapper modelMapper) {
        this._modelMapper = modelMapper;
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> RegisterUser(@RequestBody RegisterRequest register) {
         System.out.println(register.UserName + register.Email);
        var registerUser = this._modelMapper.map(register, User.class);
         System.out.println(registerUser.UserName + registerUser.Email);
         System.out.println(registerUser.UserName);
        if (registerUser != null) {
            if(_loginRegisterService.CheckUser(registerUser.UserName)!=null){
                var registerResponse = new RegisterResponse();
                registerResponse.setMessage("User Already Registered");
                return new ResponseEntity<RegisterResponse>(registerResponse, HttpStatus.BAD_REQUEST);
            }
           RegisterResponse createUser = _loginRegisterService.RegisterUser(registerUser);
           // set it  role by deault
           if(createUser.isSuccess()){
               return new ResponseEntity<RegisterResponse>(createUser, HttpStatus.OK);
           }
           else{
            return new ResponseEntity<RegisterResponse>(createUser, HttpStatus.BAD_REQUEST);
           }
        }
        return new ResponseEntity<RegisterResponse>(new RegisterResponse() , HttpStatus.BAD_REQUEST);
    }

}
