package com.example.testSpringSecurity.controllers;

import com.example.testSpringSecurity.entities.Customer;
import com.example.testSpringSecurity.repos.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DemoController {
    private final CustomerRepo customerRepo;
    @GetMapping("/hello")
    public String hello(){return "hello, world";}

    @GetMapping("/customers")
    public List<Customer> findAll(){
        return customerRepo.findAll();
    }
}
