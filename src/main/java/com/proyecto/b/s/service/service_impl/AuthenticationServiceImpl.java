package com.proyecto.b.s.service.service_impl;

import com.proyecto.b.s.dto.mapper.UserMapper;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.security.UserPrincipal;
import com.proyecto.b.s.security.jwt.JwtProvider;
import com.proyecto.b.s.dto.request.UserSignInRequestDTO;
import com.proyecto.b.s.model.User;
import com.proyecto.b.s.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(JwtProvider jwtProvider, UserMapper userMapper, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponseDTO signInAndReturnJWT(UserSignInRequestDTO userSignInRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(userSignInRequestDTO.getEmail(),
                        userSignInRequestDTO.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        User signInUser = userPrincipal.getUser();
        signInUser.setToken(jwt);
        return userMapper.userToResp(signInUser);
    }


}
