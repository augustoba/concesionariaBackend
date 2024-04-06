package com.proyecto.b.s.dto.response;


import java.time.LocalDate;
import java.util.List;

public class PersonBasicResponseDTO {
    private Long id;

    private String name;

    private String lastName;

    private String linkedin;

    private String cv;

    private String email;



    private LocalDate lastEventDate;

    public PersonBasicResponseDTO() {
    }

    public PersonBasicResponseDTO(Long id, String name, String lastName, String linkedin, String cv, String email ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.linkedin = linkedin;
        this.cv = cv;
        this.email = email;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(LocalDate lastEventDate) {
        this.lastEventDate = lastEventDate;
    }
}
