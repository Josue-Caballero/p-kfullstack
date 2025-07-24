
package com.kruger.kdevfull.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.kdevfull.dto.user.UserLoginRequest;
import com.kruger.kdevfull.dto.user.UserLoginResponse;
import com.kruger.kdevfull.service.AuthServiceI;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    
    private final AuthServiceI authService;

    @PostMapping("login")
    public UserLoginResponse loginUserObject(
        @RequestBody @Valid UserLoginRequest userRequest) {
        
        return this.authService.loginUser(userRequest);
    
    }
    
}
