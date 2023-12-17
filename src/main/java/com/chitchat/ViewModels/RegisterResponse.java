package com.chitchat.ViewModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonSerialize
public class RegisterResponse {
    @JsonProperty("email")
    public String Email;
    @JsonProperty("userName")
    public String UserName;
    @JsonProperty("success")
    public boolean Success;
    @JsonProperty("message")
    public String Message;

    public RegisterResponse() {
        this.Email = "";
        this.UserName = "";
        this.Success = false;
        this.Message = "Please Enter the Valid Input";
    }
}
