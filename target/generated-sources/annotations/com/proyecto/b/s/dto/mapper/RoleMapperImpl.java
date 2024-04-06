package com.proyecto.b.s.dto.mapper;

import com.proyecto.b.s.dto.request.RoleRequestDTO;
import com.proyecto.b.s.dto.response.RoleResponseDTO;
import com.proyecto.b.s.model.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T14:55:01-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role reqToRole(RoleRequestDTO roleRequestDTO) {
        if ( roleRequestDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleRequestDTO.getId() );
        role.setName( roleRequestDTO.getName() );

        return role;
    }

    @Override
    public RoleResponseDTO roleToResp(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();

        roleResponseDTO.setId( role.getId() );
        roleResponseDTO.setName( role.getName() );

        return roleResponseDTO;
    }

    @Override
    public List<RoleResponseDTO> roleToRespList(List<Role> roleList) {
        if ( roleList == null ) {
            return null;
        }

        List<RoleResponseDTO> list = new ArrayList<RoleResponseDTO>( roleList.size() );
        for ( Role role : roleList ) {
            list.add( roleToResp( role ) );
        }

        return list;
    }
}
