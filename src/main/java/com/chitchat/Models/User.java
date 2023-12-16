package com.chitchat.Models;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chitchat.Models.Enum.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document("Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    public ObjectId userId;
    @Field("logo")
    public String Logo;
    @Field("email")
    public String Email;
    @Field("userName")
    public String UserName;
    @Field("password")
    public String Password;
    @Field("number")
    public String Number;
    @Transient // this will ignore this property and not stored it in database...........
    public boolean Success;
    public Role Role;
    @Field("refreshToken")
    public String RefreshToken;
    @Field("refreshTokenExpireDate")
    public java.util.Date RefreshTokenExpireDate;

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
        public void setSuccess(boolean success){
        this.Success = success;
    }

       public String getLogo() {
        return this.Logo;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getUserName() {
        return this.UserName;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getNumber() {
        return this.Number;
    }
    public boolean getSuccess(){
        return this.Success;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.name()));
    }

    @Override
    public String getUsername() {
        return this.UserName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
