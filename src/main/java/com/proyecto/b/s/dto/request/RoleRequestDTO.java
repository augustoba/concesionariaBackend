package com.proyecto.b.s.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class RoleRequestDTO {
    private Long id;
    @NotBlank(message = "Debe ingresar el nombre de rol.")
    @Size(min = 2, max = 30, message = "El nombre del rol debe tener entre 2 y 20 caracteres")
    private String name;

    public RoleRequestDTO() {
    }

    public RoleRequestDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
