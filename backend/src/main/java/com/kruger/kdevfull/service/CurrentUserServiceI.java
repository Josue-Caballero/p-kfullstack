
package com.kruger.kdevfull.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CurrentUserServiceI {

    public String getUsername();
    
    public List<String> getRoles();

    public boolean isAuthenticated();

    
}
