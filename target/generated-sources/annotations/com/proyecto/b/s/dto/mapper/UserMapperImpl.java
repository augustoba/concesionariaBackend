package com.proyecto.b.s.dto.mapper;

import com.proyecto.b.s.dto.request.UserRequestDTO;
import com.proyecto.b.s.dto.request.UserUpdateRequestDTO;
import com.proyecto.b.s.dto.response.UserBasicResponseDTO;
import com.proyecto.b.s.dto.response.UserResponseDTO;
import com.proyecto.b.s.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T14:55:02-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User reqToUser(UserRequestDTO userRequestDTO) {
        if ( userRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userRequestDTO.getId() );
        user.setEmail( userRequestDTO.getEmail() );
        user.setPassword( userRequestDTO.getPassword() );
        user.setName( userRequestDTO.getName() );
        user.setLastName( userRequestDTO.getLastName() );
        user.setStatus( userRequestDTO.getStatus() );

        return user;
    }

    @Override
    public User reqUpdateToUser(UserUpdateRequestDTO userUpdateRequestDTO) {
        if ( userUpdateRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userUpdateRequestDTO.getId() );
        user.setEmail( userUpdateRequestDTO.getEmail() );
        user.setPassword( userUpdateRequestDTO.getPassword() );
        user.setName( userUpdateRequestDTO.getName() );
        user.setLastName( userUpdateRequestDTO.getLastName() );
        user.setStatus( userUpdateRequestDTO.getStatus() );

        return user;
    }

    @Override
    public UserResponseDTO userToResp(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId( user.getId() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setPassword( user.getPassword() );
        userResponseDTO.setName( user.getName() );
        userResponseDTO.setLastName( user.getLastName() );
        userResponseDTO.setStatus( user.getStatus() );
        userResponseDTO.setToken( user.getToken() );
        userResponseDTO.setRole( user.getRole() );

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> userToRespList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserResponseDTO> list = new ArrayList<UserResponseDTO>( userList.size() );
        for ( User user : userList ) {
            list.add( userToResp( user ) );
        }

        return list;
    }

    @Override
    public UserBasicResponseDTO userToBasicResp(User user) {
        if ( user == null ) {
            return null;
        }

        UserBasicResponseDTO userBasicResponseDTO = new UserBasicResponseDTO();

        userBasicResponseDTO.setId( user.getId() );
        userBasicResponseDTO.setEmail( user.getEmail() );
        userBasicResponseDTO.setPassword( user.getPassword() );
        userBasicResponseDTO.setName( user.getName() );
        userBasicResponseDTO.setLastName( user.getLastName() );
        userBasicResponseDTO.setStatus( user.getStatus() );
        userBasicResponseDTO.setToken( user.getToken() );
        userBasicResponseDTO.setRole( user.getRole() );

        return userBasicResponseDTO;
    }

    @Override
    public List<UserBasicResponseDTO> userToBasicRespList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserBasicResponseDTO> list = new ArrayList<UserBasicResponseDTO>( userList.size() );
        for ( User user : userList ) {
            list.add( userToBasicResp( user ) );
        }

        return list;
    }
}
