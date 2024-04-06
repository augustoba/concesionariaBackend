package com.proyecto.b.s.controller;

import com.proyecto.b.s.service.EmailService;
import com.proyecto.b.s.dto.request.UserUpdateRequestDTO;
import com.proyecto.b.s.dto.response.UserBasicResponseDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.rest.GenericResponse;
import com.proyecto.b.s.security.UserPrincipal;
import com.proyecto.b.s.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;

    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GenericResponse<UserBasicResponseDTO>> getUserById(@PathVariable("userId") @NotNull Long userId) {
        try {
            UserBasicResponseDTO objUserResponse = userService.findUserResponseById(userId);
            return GenericResponse.okResponse(objUserResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @PutMapping("/changestatus/{userId}")
    public ResponseEntity<GenericResponse<Void>> changeUserStatus(@PathVariable("userId") Long userId) {
        try {
            userService.changeStatus(userId);
            return GenericResponse.acceptedResponse();
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }
    
    @GetMapping("/allstatustrue")
    public ResponseEntity<GenericResponse<List<UserResponseDTO>>> getAllUserByStatusTrue() {
        try {
            List<UserResponseDTO> userResponseList = userService.findAllByStatusTrue();
            return GenericResponse.okResponse(userResponseList);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }
    
    @PutMapping("/update")
    public ResponseEntity<GenericResponse<UserResponseDTO>> updateUser(
            @Valid @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        try {
            UserResponseDTO objUserResponse = userService.updateUser(userUpdateRequestDTO);
            return GenericResponse.acceptedResponse(objUserResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<UserBasicResponseDTO>>> getAllSource() {
        try {
            List<UserBasicResponseDTO> userBasicResponseList = userService.findAllUser();
            return GenericResponse.okResponse(userBasicResponseList);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @GetMapping("/bys/usersession")
    public ResponseEntity<GenericResponse<UserResponseDTO>> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
        UserResponseDTO objUserResponse = userService.findUserNameReturnToken(userPrincipal.getUsername());
        return GenericResponse.okResponse(objUserResponse);
    }

  /*  @PostMapping("/resetpassword/{email}")
    public ResponseEntity<GenericResponse<Void>> resetUserPassword(@PathVariable("email") String email) {
        try {
            emailService.sendPasswordResetEmail(email);
            return GenericResponse.acceptedResponse();
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }*/
}
