package com.proyecto.b.s.controller;

import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.request.UserSignInRequestDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.rest.GenericResponse;
import com.proyecto.b.s.service.AuthenticationService;
import com.proyecto.b.s.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("bys/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<GenericResponse<UserResponseDTO>> signUp(@Valid @RequestBody UserRequestDTO userRequestDto) {
        try {
            UserResponseDTO objUserResponse = userService.createUser(userRequestDto);
            return GenericResponse.createdResponse(objUserResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @PostMapping("sign-in")
    public ResponseEntity<UserResponseDTO> signIn(@Valid @RequestBody UserSignInRequestDTO userSignInRequestDTO) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(userSignInRequestDTO), HttpStatus.OK);
    }
}
