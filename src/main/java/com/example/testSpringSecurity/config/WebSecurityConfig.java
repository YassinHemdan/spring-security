package com.example.testSpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig {
    // UserDetailsService an interface  -> manages the user credentials
    @Bean
    public UserDetailsService userDetailsService(){
        // InMemoryUserDetailsManager implements the UserDetailsService
        // in future -> we will check the credentials of a user from our DB

        var uds = new InMemoryUserDetailsManager(); // now we want to add a user in our InMemory


        var user1 = User.withUsername("yassin")
                .password("123")
                .authorities("read")
                .build();

        var user2 = User.withUsername("mohamed")
                .password("abc")
                .authorities("write")
                .build();

        // we add our users
        // in InMemoryUserDetailsManager, our users are put in a map -> imagine this map is our DB :)
        uds.createUser(user1);
        uds.createUser(user2);

        return uds;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); // its deprecated
    }
}
