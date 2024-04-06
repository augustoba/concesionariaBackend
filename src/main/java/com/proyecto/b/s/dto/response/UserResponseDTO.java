package com.proyecto.b.s.dto.response;

import com.proyecto.b.s.model.Role;

import javax.persistence.Transient;

public class UserResponseDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Boolean status;
    @Transient
    private String token;
    private Role role;



    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String email, String password, String name, String lastName, Boolean status, String token, Role role)

            {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.status = status;
        this.token = token;
        this.role = role;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
