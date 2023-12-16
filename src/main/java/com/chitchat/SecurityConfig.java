package com.chitchat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chitchat.Services.JwtService;

@Configuration
public class SecurityConfig{

    @Autowired
    private JwtService _jwtService;
    //private JwtFilterConfig _jwtFilterConfig;

    @Autowired
    private JwtFilterConfig _jwtFilterConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // Now we will perform security on all request insted of register and login endpoints
        // http.authorizeHttpRequests((authz)->
        //     authz.anyRequest().authenticated()
        // );

        http.csrf(csrf->csrf.disable()).
        cors(cors->cors.disable()).
        authorizeHttpRequests(authorize->authorize.
        requestMatchers("/api/login").permitAll()
        .requestMatchers("/register").permitAll().anyRequest()
        .authenticated()).
        exceptionHandling(exp->exp.authenticationEntryPoint(_jwtService))
        .sessionManagement(sessioManagement->sessioManagement.
        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        http.addFilterBefore(_jwtFilterConfig,UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
