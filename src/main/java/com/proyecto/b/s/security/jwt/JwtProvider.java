package com.proyecto.b.s.security.jwt;

import com.proyecto.b.s.security.UserPrincipal;
import com.proyecto.b.s.model.User;
import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    String generateToken (User user);

    boolean isTokenValid(HttpServletRequest request);
}
