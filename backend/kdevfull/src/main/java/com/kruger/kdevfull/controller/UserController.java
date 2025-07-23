
package com.kruger.kdevfull.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("path")
    public String getMethodName() {
        
        return new String("Hello");
    
    }
    
}
