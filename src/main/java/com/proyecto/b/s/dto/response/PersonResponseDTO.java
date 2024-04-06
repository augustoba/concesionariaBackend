package com.proyecto.b.s.dto.response;


import java.time.LocalDate;

public class PersonResponseDTO {

    private Long id;

    private String name;

    private String lastName;

    private String linkedin;

    private String cv;

    private LocalDate contactDate;

    private String dni;

    private String email;

    private String cuil;

    private String phoneNumber;

    private String remuneration;



    private String observations;


    private UserResponseDTO userResponseDTO;



    public PersonResponseDTO() {
    }

    public PersonResponseDTO(Long id, String name, String lastName, String linkedin, String cv, LocalDate contactDate, String dni, String email, String cuil, String phoneNumber, String remuneration,  UserResponseDTO userResponseDTO) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.linkedin = linkedin;
        this.cv = cv;
        this.contactDate = contactDate;
        this.dni = dni;
        this.email = email;
        this.cuil = cuil;
        this.observations=observations;
        this.phoneNumber = phoneNumber;
        this.remuneration = remuneration;
        this.userResponseDTO = userResponseDTO;

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

    public LocalDate getContactDate() {
        return contactDate;
    }

    public void setContactDate(LocalDate contactDate) {
        this.contactDate = contactDate;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
    }

    public UserResponseDTO getUserResponseDTO() {
        return userResponseDTO;
    }

    public void setUserResponseDTO(UserResponseDTO userResponseDTO) {
        this.userResponseDTO = userResponseDTO;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
