
package com.kruger.kdevfull.service;

import org.springframework.stereotype.Service;

@Service
public interface CurrentUserServiceI {

    public String getUsername();

    public boolean isAuthenticated();
    
}
