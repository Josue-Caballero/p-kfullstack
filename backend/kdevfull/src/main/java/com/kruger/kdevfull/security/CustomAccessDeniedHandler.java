
package com.kruger.kdevfull.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.kruger.kdevfull.exception.ErrorMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
        HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) 
        throws IOException, ServletException {
        
        response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(
                new ErrorMessage(
                    HttpStatus.FORBIDDEN.value(),
                    new Date(),
                    "Unauthorized",
                    null).toString());
                
        
    }

}
