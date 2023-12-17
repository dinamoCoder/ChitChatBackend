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
    private String Email;
    @JsonProperty("userName")
    private String UserName;
    @JsonProperty("success")
    private boolean Success;
    @JsonProperty("message")
    private String Message;

    public RegisterResponse() {
        this.Email = "";
        this.UserName = "";
        this.Success = false;
        this.Message = "Please Enter the Valid Input";
    }


}
