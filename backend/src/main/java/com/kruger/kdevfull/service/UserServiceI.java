package com.kruger.kdevfull.service;


import java.util.List;

import com.kruger.kdevfull.dto.user.UserRequest;
import com.kruger.kdevfull.dto.user.UserResponse;

public interface UserServiceI {

    UserResponse create(UserRequest request);
    
    List<UserResponse> findAll();
    
    UserResponse findById(Long id);

}
