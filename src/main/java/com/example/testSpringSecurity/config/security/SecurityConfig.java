package com.example.testSpringSecurity.config.security;

import com.example.testSpringSecurity.config.security.filters.CustomAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic(Customizer.withDefaults())
                .addFilterBefore(new CustomAuthenticationFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().permitAll()
                )
                .build();
    }
}
