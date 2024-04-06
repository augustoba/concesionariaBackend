package com.proyecto.b.s.dto.request;

import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class PersonRequestDTO {

    private Long id;
    @NotBlank(message = "Debe ingresar el nombre de la persona.")
    @Size(min = 2, max = 30, message = "El nombre de la persona debe tener entre 2 y 30 caracteres")
    private String name;
    @NotBlank(message = "Debe ingresar el apellido de la persona.")
    @Size(min = 2, max = 30, message = "El apellido de la persona debe tener entre 2 y 30 caracteres")
    private String lastName;

@URL
    private String linkedin;
    private String cv;

    private LocalDate contactDate;

    private String dni;

    private String email;

    private String cuil;

    private String phoneNumber;
    @Size(max = 12, message = "La remuneración debe tener un máximo de 12 caracteres")
    private String remuneration;

    @Size(max = 280, message = "La observacion puede tener un máximo de 280 caracteres")
    private String observations;

    @NotNull(message ="El seniority no puede estar vacio.")
    private Long seniorityId;

    private Long userId;

    @NotEmpty(message = "Debe ingresar al menos una skill.")
    private List<Long> skillListId;

    private Long industryId;

    @NotNull(message = "Debe ingresar la fuente de contacto.")
    private Long sourceId;

    @NotNull(message = "Debe ingresar una profesion.")
    private Long professionId;

    private Long statusPersonId;

    private List<Long> searchIdList;

    private List<Long> eventIdList;

    private List<Long> pastSearchIdList;

    public PersonRequestDTO() {
    }

    public PersonRequestDTO(Long id, String name, String lastName, String linkedin, String cv, LocalDate contactDate, String dni, String email, String cuil, String phoneNumber, String remuneration, Long seniorityId, Long userId, List<Long> skillListId, Long industryId, Long sourceId, Long professionId, Long statusPersonId, List<Long> searchIdList, List<Long> eventIdList, List<Long> pastSearchIdList, String observations) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.linkedin = linkedin;
        this.cv = cv;
        this.contactDate = contactDate;
        this.dni = dni;
        this.email = email;
        this.cuil = cuil;
        this.phoneNumber = phoneNumber;
        this.remuneration = remuneration;
        this.seniorityId = seniorityId;
        this.userId = userId;
        this.skillListId = skillListId;
        this.industryId = industryId;
        this.sourceId = sourceId;
        this.professionId = professionId;
        this.statusPersonId = statusPersonId;
        this.searchIdList = searchIdList;
        this.eventIdList = eventIdList;
        this.pastSearchIdList = pastSearchIdList;
        this.observations=observations;
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

    public Long getSeniorityId() {
        return seniorityId;
    }

    public void setSeniorityId(Long seniorityId) {
        this.seniorityId = seniorityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getSkillListId() {
        return skillListId;
    }

    public void setSkillListId(List<Long> skillListId) {
        this.skillListId = skillListId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
    }

    public Long getStatusPersonId() {
        return statusPersonId;
    }

    public void setStatusPersonId(Long statusPersonId) {
        this.statusPersonId = statusPersonId;
    }

    public List<Long> getSearchIdList() {
        return searchIdList;
    }

    public void setSearchIdList(List<Long> searchIdList) {
        this.searchIdList = searchIdList;
    }

    public List<Long> getEventIdList() {
        return eventIdList;
    }

    public void setEventIdList(List<Long> eventIdList) {
        this.eventIdList = eventIdList;
    }

    public List<Long> getPastSearchIdList() {
        return pastSearchIdList;
    }

    public void setPastSearchIdList(List<Long> pastSearchIdList) {
        this.pastSearchIdList = pastSearchIdList;
    }

    public String getObservations() {
        return observations;
    }
    public void setObservations(String observations) {
        this.observations = observations;
    }
}