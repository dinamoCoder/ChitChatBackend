package com.chitchat;
import java.io.IOException;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chitchat.Models.User;
import com.chitchat.Services.JwtService;
import com.chitchat.Services.LoginRegisterService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtFilterConfig extends OncePerRequestFilter  {

   @Autowired
   private JwtService _jwtService;

   @Autowired
   private LoginRegisterService _loginRegisterService;


   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
           /* this will validate each request and check jwt token is in it or not.
               The steps we perform here are:
                  1. Get token from request.
                  2. Validate token
                  3. GetUserName from token
                  4. Load user associated with this token.
                  5. set authentication
           */
         String token = request.getHeader("Authorization");
         if(token!=null && token.startsWith("Bearer")){
            token = token.substring(7); 
            String getUserId = "";
            try{
                getUserId = _jwtService.getUserIdFromToken(token);
            }
            catch(IllegalArgumentException e){
               e.printStackTrace();
            } catch(ExpiredJwtException e){
               System.out.println("Your token will be expired");
            }catch(MalformedJwtException e){
              e.printStackTrace();
            }
            catch(Exception e){
               e.printStackTrace();
            }
           
            if(getUserId!="" && SecurityContextHolder.getContext().getAuthentication()== null){
              User userDataFromDb =  _loginRegisterService.validateUserId(getUserId);
              if(userDataFromDb!=null){
               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDataFromDb,null,userDataFromDb.getAuthorities());
               authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

               SecurityContextHolder.getContext().setAuthentication(authentication);
              }
              else{
               System.out.println("Validation Fails!!!!!");
              }
            }
            else{
               System.out.println("Not getting the data from token");
            }
             
         }
   
        filterChain.doFilter(request, response);
      }



 

        
    }

