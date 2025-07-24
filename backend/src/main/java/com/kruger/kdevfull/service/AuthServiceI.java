package com.kruger.kdevfull.service;

import com.kruger.kdevfull.dto.user.UserLoginRequest;
import com.kruger.kdevfull.dto.user.UserLoginResponse;

public interface AuthServiceI {
    
    public UserLoginResponse loginUser(UserLoginRequest user);

}
