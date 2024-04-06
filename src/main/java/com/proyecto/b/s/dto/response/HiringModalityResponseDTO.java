package com.proyecto.b.s.dto.response;


public class HiringModalityResponseDTO {

    private Long id;
    private String name;
    private Boolean status;

    public HiringModalityResponseDTO() {
    }

    public HiringModalityResponseDTO(Long id, String name, Boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
