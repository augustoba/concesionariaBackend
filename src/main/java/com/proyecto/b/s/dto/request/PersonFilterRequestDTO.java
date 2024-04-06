package com.proyecto.b.s.dto.request;

import java.util.List;

public class PersonFilterRequestDTO {
    private String name;
    private String lastName;
    private Long professionId;
    private Long seniorityId;
    private String linkedin;
    private Long userId;
    private Long searchId;
    private Integer searchYear;
    private Integer searchMonth;
    private String searchDay;
    private Integer page;
    private Integer size;
    private List<Long> skillIdList;

    public PersonFilterRequestDTO() {
    }

    public PersonFilterRequestDTO(String name, String lastName, Long professionId, Long seniorityId, String linkedin, Long userId, Long searchId, Integer searchYear, Integer searchMonth, String searchDay, Integer page, Integer size, List<Long> skillIdList) {
        this.name = name;
        this.lastName = lastName;
        this.professionId = professionId;
        this.seniorityId = seniorityId;
        this.linkedin = linkedin;
        this.userId = userId;
        this.searchId = searchId;
        this.searchYear = searchYear;
        this.searchMonth = searchMonth;
        this.searchDay = searchDay;
        this.page = page;
        this.size = size;
        this.skillIdList = skillIdList;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
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

    public Long getProfessionId() {
        return professionId;
    }

    public void setProfessionId(Long professionId) {
        this.professionId = professionId;
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

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public Integer getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(Integer searchYear) {
        this.searchYear = searchYear;
    }

    public Integer getSearchMonth() {
        return searchMonth;
    }

    public void setSearchMonth(Integer searchMonth) {
        this.searchMonth = searchMonth;
    }

    public String getSearchDay() {
        return searchDay;
    }

    public void setSearchDay(String searchDay) {
        this.searchDay = searchDay;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Long> getSkillIdList() {
        return skillIdList;
    }

    public void setSkillIdList(List<Long> skillIdList) {
        this.skillIdList = skillIdList;
    }
}
