package com.proyecto.b.s.dto.mapper;

import com.proyecto.b.s.dto.request.RoleRequestDTO;
import com.proyecto.b.s.dto.response.RoleResponseDTO;
import com.proyecto.b.s.model.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role reqToRole(RoleRequestDTO roleRequestDTO);

    RoleResponseDTO roleToResp(Role role);

    List<RoleResponseDTO> roleToRespList(List<Role> roleList);
}
