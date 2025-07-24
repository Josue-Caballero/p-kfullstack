
package com.kruger.kdevfull.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.kruger.kdevfull.dto.user.UserLoginRequest;
import com.kruger.kdevfull.dto.user.UserLoginResponse;
import com.kruger.kdevfull.security.JWTProvider;
import com.kruger.kdevfull.service.AuthServiceI;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceToken implements AuthServiceI {

    private final AuthenticationManager authManager;

    private final JWTProvider jwtProvider;

    @Override
    public UserLoginResponse loginUser(UserLoginRequest user)  {

        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(
                user.getUsername(), 
                user.getPassword()
            );
        

        return new UserLoginResponse(
            jwtProvider.generateToken(authManager.authenticate(authToken))
            , "refresh_token"
        );
        
    }
    
}
