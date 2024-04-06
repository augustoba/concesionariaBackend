package com.proyecto.b.s.service.service_impl;

import com.proyecto.b.s.dto.mapper.RoleMapper;
import com.proyecto.b.s.dto.request.RoleRequestDTO;
import com.proyecto.b.s.dto.response.RoleResponseDTO;
import com.proyecto.b.s.exception.ResourceAlreadyExistsException;
import com.proyecto.b.s.exception.ResourceEntityNullException;
import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.model.Role;
import com.proyecto.b.s.repository.RoleRepository;
import com.proyecto.b.s.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        if (roleRequestDTO == null) {
            throw new ResourceEntityNullException("Rol");
        }

        Role objActualRole = roleRepository.findByName(roleRequestDTO.getName());

        if (objActualRole != null) {
            throw new ResourceAlreadyExistsException("existDB", "Rol");
        }

        Role objRole = roleMapper.reqToRole(roleRequestDTO);
        roleRepository.save(objRole);

        return roleMapper.roleToResp(objRole);
    }

    @Override
    @Transactional
    public RoleResponseDTO updateRole(RoleRequestDTO roleRequestDTO) throws ResourceNotFoundException {
        if (roleRequestDTO == null) {
            throw new ResourceEntityNullException("Rol");
        }

        Optional<Role> objRoleOpt = roleRepository.findById(roleRequestDTO.getId());

        if (objRoleOpt.isPresent()) {
            Role objRole = objRoleOpt.get();
            objRole.setName(roleRequestDTO.getName());
            roleRepository.save(objRole);
            return roleMapper.roleToResp(objRole);
        } else {
            throw new ResourceNotFoundException("noExistDB", "Rol");
        }

    }

    @Override
    public List<RoleResponseDTO> findAllRoles() throws ResourceNotFoundException {
        List<Role> roleList = roleRepository.findAllByOrderByName();
        if (roleList.isEmpty()) {
            throw new ResourceNotFoundException("noDataFound", "");
        }
        return roleMapper.roleToRespList(roleList);
    }

    @Override
    public Role findRoleById(Long id) throws ResourceNotFoundException {
        Role role = null;
        Optional<Role> roleAux = roleRepository.findById(id);

        if (roleAux.isPresent()) {
            role = roleAux.get();
        } else {
            throw new ResourceNotFoundException("noExistDB", "Rol");
        }
        return role;
    }


}
