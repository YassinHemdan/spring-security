package com.example.testSpringSecurity.config.security.filters;

import com.example.testSpringSecurity.config.security.authentication.CustomAuthentication;
import com.example.testSpringSecurity.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// HOW OUR CUSTOM FILTER WORKS

// The request has a key in its header
// We take this key from it and compare it with a key stored with us
@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager manager;

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
        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication ca = new CustomAuthentication(false, key);
        var a = manager.authenticate(ca);

        if(a.isAuthenticated()){ // our authentication object is authenticated successfully
            SecurityContextHolder.getContext().setAuthentication(a); // add it to our SecurityContextHolder
            filterChain.doFilter(request, response); // go to the next filter
        }
    }
}
