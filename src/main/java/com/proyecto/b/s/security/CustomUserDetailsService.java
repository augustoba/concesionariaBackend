package com.proyecto.b.s.security;

import com.proyecto.b.s.model.User;
import com.proyecto.b.s.service.UserService;
import com.proyecto.b.s.util.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El email no fue encontrado: " + email));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().getName()));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .userName(email)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }


}
