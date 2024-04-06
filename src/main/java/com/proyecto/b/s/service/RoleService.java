package com.proyecto.b.s.service;

import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.dto.request.RoleRequestDTO;
import com.proyecto.b.s.dto.response.RoleResponseDTO;
import com.proyecto.b.s.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    @Transactional
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);


    RoleResponseDTO updateRole(RoleRequestDTO roleRequestDTO) throws ResourceNotFoundException;

    List<RoleResponseDTO> findAllRoles() throws ResourceNotFoundException;

    Role findRoleById(Long id) throws ResourceNotFoundException;


}
