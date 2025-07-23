
package com.kruger.kdevfull.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kruger.kdevfull.dto.user.UserRequest;
import com.kruger.kdevfull.dto.user.UserResponse;
import com.kruger.kdevfull.mapper.UserMapper;
import com.kruger.kdevfull.models.User;
import com.kruger.kdevfull.repository.UserRepository;
import com.kruger.kdevfull.service.CurrentUserServiceI;
import com.kruger.kdevfull.service.UserServiceI;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceI {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CurrentUserServiceI authorizationContext;
    
    @Override
    public UserResponse create(UserRequest request) {
        
        if (userRepository.existsByUsername(request.getUsername()))
            throw new IllegalArgumentException("Username already exists");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new IllegalArgumentException("Email already exists");

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        user = userRepository.save(user);

        return userMapper.toResponse(user);

    }

    @Override
    public List<UserResponse> findAll() {

        return userMapper.toResponseList(
            userRepository.findAllByCreatedBy(authorizationContext.getUsername())
        );

    }

    @Override
    public UserResponse findById(Long id) {

        return userRepository.findByIdAndCreatedBy(
            id, 
            authorizationContext.getUsername()
        ).map(userMapper::toResponse)
        .orElseThrow();

    }

}
