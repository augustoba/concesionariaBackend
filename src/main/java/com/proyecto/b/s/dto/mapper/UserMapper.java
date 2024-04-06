package com.proyecto.b.s.dto.mapper;

import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.request.UserUpdateRequestDTO;
import com.proyecto.b.s.dto.response.UserBasicResponseDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User reqToUser(UserRequestDTO userRequestDTO);
    User reqUpdateToUser(UserUpdateRequestDTO userUpdateRequestDTO);


    UserResponseDTO userToResp(User user);

    List<UserResponseDTO> userToRespList(List<User> userList);

    UserBasicResponseDTO userToBasicResp(User user);

    List<UserBasicResponseDTO> userToBasicRespList(List<User> userList);
}
