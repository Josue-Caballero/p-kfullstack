package com.kruger.kdevfull.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kruger.kdevfull.exception.ErrorMessage;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (request.getRequestURI().contains("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        
        try {

            final String username = jwtProvider.extractUsername(token);

            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtProvider.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }

            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {

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

}
