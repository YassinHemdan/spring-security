package com.example.testSpringSecurity.security;

import com.example.testSpringSecurity.entities.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

// another adapter
@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final Authority authority;
    @Override
    public String getAuthority() {
        return authority.getAuthority_name();
    }
}

