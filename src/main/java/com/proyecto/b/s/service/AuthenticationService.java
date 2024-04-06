package com.proyecto.b.s.service;

import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.dto.request.UserSignInRequestDTO;

public interface AuthenticationService {
    UserResponseDTO signInAndReturnJWT(UserSignInRequestDTO userSignInRequestDTO);

}
