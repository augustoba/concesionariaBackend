package com.proyecto.b.s.security;

import com.proyecto.b.s.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private Long id;
    private String email;
    transient private String password;
    transient private User user;
    private Set<GrantedAuthority> authorities;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String email, String password, User user, Set<GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }
    public static UserPrincipalBuilder builder() {
        return new UserPrincipalBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }
}
