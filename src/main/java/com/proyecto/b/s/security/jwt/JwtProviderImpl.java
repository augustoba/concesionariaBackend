package com.proyecto.b.s.security.jwt;

import com.proyecto.b.s.model.User;
import com.proyecto.b.s.security.UserPrincipal;
import com.proyecto.b.s.util.SecurityUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class JwtProviderImpl implements JwtProvider{
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;
    @Override
    public String generateToken(UserPrincipal auth){
        String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Key key= Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("id", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private Claims extractClaims(HttpServletRequest request){
        String token = SecurityUtils.extractAuthTokenFromRequest(request);
        if (token == null){
            return null;
        }
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    @Override
    public Authentication getAuthentication(HttpServletRequest request){
        Claims claims = extractClaims(request);
        if (claims == null){
            return null;
        }
        String username = claims.getSubject();
        Long userId = claims.get("id",Long.class);
        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());
        UserDetails userDetails = UserPrincipal.builder().userName(username)
                .authorities(authorities)
                .id(userId)
                .build();
        if(username == null){
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }
    @Override
    public String generateToken(User user){
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles",user.getRole())
                .claim("id",user.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    @Override
    public boolean isTokenValid(HttpServletRequest request) {
        Claims claims = extractClaims(request);
        if(claims == null){
            return false;
        }
        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

}
