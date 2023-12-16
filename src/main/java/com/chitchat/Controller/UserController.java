package com.chitchat.Controller;

import org.springframework.context.annotation.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api")
public class UserController {


    
    @GetMapping("/getUsers")
    public void GetUsers(){
        System.out.println("Getting the users");
    }
    
}
