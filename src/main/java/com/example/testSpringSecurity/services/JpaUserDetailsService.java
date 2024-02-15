package com.example.testSpringSecurity.services;

import com.example.testSpringSecurity.entities.Customer;
import com.example.testSpringSecurity.repos.CustomerRepo;
import com.example.testSpringSecurity.security.SecurityCustomer;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// Implementation of UserDetailsService for spring security
@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final CustomerRepo customerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findCustomerByUserName(username);
        //System.out.println(customer.getAuthorities());
        if(customer == null)
            throw new UsernameNotFoundException("username not found " + username);
        else
            return new SecurityCustomer(customer);
    }
}
