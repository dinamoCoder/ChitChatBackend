package com.chitchat.Services;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chitchat.Models.User;
import com.chitchat.Models.Enum.Role;
import com.chitchat.Services.IRepository.ILoginRegisterRepository;
import com.chitchat.ViewModels.LoginRequest;
import com.chitchat.ViewModels.LoginResponse;
import com.chitchat.ViewModels.RegisterResponse;

@Service
public class LoginRegisterService {

    @Autowired
    private ILoginRegisterRepository<User> _loginRegisterRepository;
    @Autowired
    private ModelMapper _modelMapperDto;
    @Autowired
    private PasswordEncoder _passwordEncoder;
    @Autowired
    private JwtService _jwtService;
    

    public RegisterResponse RegisterUser(User user) {
        RegisterResponse registerResponse = new RegisterResponse();
        user.Password = _passwordEncoder.encode(user.Password);
        // we will create the refresh token for user........
        user.RefreshToken = UUID.randomUUID().toString();
        user.Role = Role.User;
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH, 2);
        user.RefreshTokenExpireDate = calender.getTime();
        var createUser = _loginRegisterRepository.save(user);
        if (createUser != null) {
            // then we will return the RegisterResponse
            createUser.Success =true;
            registerResponse = _modelMapperDto.map(user, RegisterResponse.class);
            registerResponse.Message = "Registered Successfully!!!!!";
            return registerResponse;
        }
        return registerResponse;
    }
   

    public User CheckUser(String userName){
       User getUserData =  _loginRegisterRepository.findByUserName(userName);
        return getUserData;
    }

    public LoginResponse LoginUser(LoginRequest login){
        
        LoginResponse loginResponse = new LoginResponse();
        var checkUser = CheckUser(login.UserName);
        if(checkUser!=null){
            // then we will check its password and other work so on
            var encodedPassword = _passwordEncoder.encode(login.Password); 
            var checkPassword = _passwordEncoder.matches(login.Password,encodedPassword);
            if(checkPassword){
                HashMap<String,Object> mapExtraClaims = new HashMap<String,Object>();
                mapExtraClaims.put("userId", checkUser.userId.toString());
            //TODO:  this role will be changed later on just use static
                mapExtraClaims.put("Role", checkUser.Role);
                // then we will genereate the token and send it to the client.
                loginResponse.Token = _jwtService.generateToken(mapExtraClaims, checkUser);
                loginResponse.RefreshToken = checkUser.RefreshToken;
                loginResponse.ErrorMessage = "Login Successfully!!!!";
                return loginResponse;
            }
            loginResponse.ErrorMessage = "Please enter a correct password";
            return loginResponse;
        }
        loginResponse.ErrorMessage="You enter a wrong ceredentials";
        return loginResponse;
    }



    public User validateUserId(String UserId){
       var data = _loginRegisterRepository.findByuserId(UserId);
       return data;
    }
}
