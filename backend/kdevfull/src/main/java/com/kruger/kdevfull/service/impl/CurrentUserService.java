
package com.kruger.kdevfull.service.impl;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    
    public List<String> getRoles() {
    
        Authentication auth = SecurityContextHolder
            .getContext().getAuthentication();
        
        return auth.getAuthorities()
            .stream()
            .map( a -> a.toString() ).toList();

    }

}
