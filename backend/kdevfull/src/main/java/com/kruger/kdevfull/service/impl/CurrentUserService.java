
package com.kruger.kdevfull.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kruger.kdevfull.service.CurrentUserServiceI;

@Service
public class CurrentUserService implements CurrentUserServiceI {

    public String getUsername() {
        
        Authentication auth = SecurityContextHolder
            .getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
        
            return auth.getName();
        
        }

        return null;
        
    }
    
    public boolean isAuthenticated() {
    
        Authentication auth = SecurityContextHolder
            .getContext().getAuthentication();
        
        return auth != null && auth.isAuthenticated();

    }

}
