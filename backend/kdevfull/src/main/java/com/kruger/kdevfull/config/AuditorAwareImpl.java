
package com.kruger.kdevfull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;

@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder
            .getContext().getAuthentication();

        if (auth == null 
            || !auth.isAuthenticated() 
            || auth.getPrincipal().equals("anonymousUser")) {
            
            return Optional.empty();
        
        }

        return Optional.of(auth.getName()); // o extrae el username
    }

    @Bean
    public AuditorAware<String> auditorAware() {

        return new AuditorAwareImpl();

    }

}
