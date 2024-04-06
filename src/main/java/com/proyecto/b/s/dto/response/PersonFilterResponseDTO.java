package com.proyecto.b.s.dto.response;

import java.util.List;

public class PersonFilterResponseDTO {

    private Long countPage;
    private List<PersonBasicResponseDTO> personResponseDTOList;
    public PersonFilterResponseDTO() {
    }

    public PersonFilterResponseDTO(Long countPage, List<PersonBasicResponseDTO> personResponseDTOList) {
        this.countPage = countPage;
        this.personResponseDTOList = personResponseDTOList;
    }

    public Long getCountPage() {
        return countPage;
    }

    public void setCountPage(Long countPage) {
        this.countPage = countPage;
    }

    public List<PersonBasicResponseDTO> getPersonResponseDTOList() {
        return personResponseDTOList;
    }

    public void setPersonResponseDTOList(List<PersonBasicResponseDTO> personResponseDTOList) {
        this.personResponseDTOList = personResponseDTOList;
    }
}
