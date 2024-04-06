package com.proyecto.b.s.service;

import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.request.UserUpdateRequestDTO;
import com.proyecto.b.s.dto.response.UserBasicResponseDTO;
import com.proyecto.b.s.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws ResourceNotFoundException;
    void changeStatus(Long userId) throws ResourceNotFoundException;

    List<UserResponseDTO> findAllByStatusTrue() throws ResourceNotFoundException;

    UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO) throws ResourceNotFoundException;

    List<UserBasicResponseDTO> findAllUser() throws ResourceNotFoundException;

    Optional<User> findByEmail(String email);

    UserResponseDTO findUserNameReturnToken(String username);

    User findById(Long userId) throws ResourceNotFoundException;

    void saveUser(User user);

    void changeUserPassword(String email, String password) throws ResourceNotFoundException;

    User userList(String excelImportSearch);
    User usuarios(String excelImport);

    User findByName(String name);

    UserBasicResponseDTO findUserResponseById(Long userId) throws ResourceNotFoundException;;
}
