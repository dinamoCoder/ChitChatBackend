package com.chitchat.ViewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {
    public String Email;
    public String UserName;
    public boolean Success;
    public String Message;

    public RegisterResponse() {
        this.Email = "";
        this.UserName = "";
        this.Success = false;
        this.Message = "Please Enter the Valid Input";
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public void setSuccess(boolean success) {
        this.Success = success;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

}
