
package com.kruger.kdevfull.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kruger.kdevfull.dto.user.UserRequest;
import com.kruger.kdevfull.dto.user.UserResponse;
import com.kruger.kdevfull.service.UserServiceI;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceI userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.create(request));
        
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        
        return ResponseEntity.ok(userService.findById(id));

    }
    
}
