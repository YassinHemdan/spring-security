package com.example.testSpringSecurity.config.security.filters;

import com.example.testSpringSecurity.config.security.authentication.CustomAuthentication;
import com.example.testSpringSecurity.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// HOW OUR CUSTOM FILTER WORKS

// The request has a key in its header
// We take this key from it and compare it with a key stored with us

public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager manager = new CustomAuthenticationManager();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        /*
         * 1- create an authentication object which is not authenticated yet
         * 2- delegate the authentication object to the manager
         * 3- get back the authentication from the manager
         * 4- if the object is authenticated, then request to the next filter in the chain
         */

        // get the key from the request -> located at the header
        String key = String.valueOf(request.getHeader("x-api-key"));
        if (key == null || "null".equals(key)) {
            filterChain.doFilter(request, response);
        }

        CustomAuthentication ca = new CustomAuthentication(false, key);

        try{
            var auth = manager.authenticate(ca);
            if(auth.isAuthenticated()){ // our authentication object is authenticated successfully
                SecurityContextHolder.getContext().setAuthentication(auth); // add it to our SecurityContextHolder
                filterChain.doFilter(request, response); // go to the next filter
            }
            else
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }catch (AuthenticationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}