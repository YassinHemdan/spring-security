package com.example.testSpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    /***
     *
     * The below configuration of UserDetailsService is messy
     * We saw that we have to provide a customer repo in our config class
     * But we could avoid that by annotating the JpaUserDetailsService by @Service
     * Now when a bean is created by the ioc context, the class is implementing the
     *     UserDetailsService interface, so it will be noticed by the spring security
     *
     */

    /*
    private final CustomerRepo customerRepo;
    @Bean
    public UserDetailsService userDetailsService(){
        // in UserDetailsService interface there is only one method -> loadUserByUsername
        // this method takes a username of type string return an object of type UserDetails
        // we should implement it -> in services
        return new JpaUserDetailsService();
    }
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); // should not use this in production
    }
}
