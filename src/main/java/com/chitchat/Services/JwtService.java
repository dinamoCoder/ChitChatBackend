package com.chitchat.Services;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtService implements AuthenticationEntryPoint  {
     @Autowired
     private Environment environment;
     
    // this will generate the token
    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
      try{
        // here we will get the key from the spplication file.
          byte[] keyBytes = Decoders.BASE64.decode(environment.getProperty("jwt.secret-key"));
          Key key = Keys.hmacShaKeyFor(keyBytes);
          return Jwts.builder().addClaims(extraClaims).setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()*1000*1))
        .signWith(key,SignatureAlgorithm.HS512).compact();
      }
      catch(Exception ex){
        System.out.println(ex);
        return "";
      }
      }

      
    

      public String getUserIdFromToken(String token){
        byte[] keyBytes = Decoders.BASE64.decode(environment.getProperty("jwt.secret-key"));
          Key key = Keys.hmacShaKeyFor(keyBytes);
        Claims getData = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        if(getData.containsKey("userId")){
          var id = getData.get("userId");
          return id.toString();
        }
        return "";
      }


      @Override
      public void commence(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException authException) throws IOException, ServletException {
         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         PrintWriter writer = response.getWriter();
         writer.println("Access Denied !! "+authException.getMessage());
      }
          
}




