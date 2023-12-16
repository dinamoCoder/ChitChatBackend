package com.chitchat.ViewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    public String Logo;
    public String Email;
    public String UserName;
    public String Password;
    public String Number;

    public void setLogo(String logo) {
        this.Logo = logo;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setNumber(String number) {
        this.Number = number;
    }
    public String getLogo() {
        return this.Logo;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getUserName() {
        return this.UserName ;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getNumber() {
       return  this.Number;
    }
}
