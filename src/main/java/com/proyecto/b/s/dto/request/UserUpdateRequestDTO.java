package com.proyecto.b.s.dto.request;

import javax.validation.constraints.*;
import java.util.List;

public class UserUpdateRequestDTO {
    private Long id;
    @NotEmpty(message = "debe ingresar un email.")
    @NotNull(message = "debe ingresar un email.")
    @NotBlank(message = "debe ingresar un email.")
    @Email(message="Debe ingresar un email valido.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un número y un carácter especial")
    private String password;
    @Size(min = 2, message = "Debe ingresar un nombre mayor a 2 caracteres.")
    private String name;
    @Size(min = 2, message = "Debe ingresar un apellido mayor a 2 caracteres.")
    private String lastName;
    private Boolean status;
    @NotNull(message = "Debe ingresar un rol.")
    private Long roleId;

    private List<Long> searchIdList;


    public UserUpdateRequestDTO() {
    }

    public UserUpdateRequestDTO(Long id, String email, String password, String name, String lastName, Boolean status, Long roleId, List<Long> searchIdList) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.roleId = roleId;
        this.searchIdList = searchIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getSearchIdList() {
        return searchIdList;
    }

    public void setSearchIdList(List<Long> searchIdList) {
        this.searchIdList = searchIdList;
    }
}


