package com.proyecto.b.s.controller;

import com.proyecto.b.s.exception.ResourceNotFoundException;
import com.proyecto.b.s.dto.request.RoleRequestDTO;
import com.proyecto.b.s.dto.response.RoleResponseDTO;
import com.proyecto.b.s.rest.GenericResponse;
import com.proyecto.b.s.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<RoleResponseDTO>> createRole(@RequestBody @Valid RoleRequestDTO roleRequestDTO) {
        RoleResponseDTO objRoleResponse = roleService.createRole(roleRequestDTO);
        return GenericResponse.createdResponse(objRoleResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse<RoleResponseDTO>> updateRole(
            @Valid @RequestBody RoleRequestDTO roleRequestDTO) {
        try {
            RoleResponseDTO objRoleResponse = roleService.updateRole(roleRequestDTO);
            return GenericResponse.acceptedResponse(objRoleResponse);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<RoleResponseDTO>>> getAllRoles() {
        try {
            List<RoleResponseDTO> roleResponseList = roleService.findAllRoles();
            return GenericResponse.okResponse(roleResponseList);
        } catch (ResourceNotFoundException e) {
            return GenericResponse.exceptionResponse(e.getHttpStatus());
        }
    }
}
