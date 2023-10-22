package com.example.lagaltcaseapplication.dto;

import com.example.lagaltcaseapplication.enums.Skills;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long userId;
    private boolean isProfileVisible;
    private String forName;
    private String lastName;
    private String description;
    private String country;
    private String email;
    private String userRole;

    @Getter
    private boolean includeProjects;

    private List<ProjectDTO> projects;

    private String username;
    private String password;
    private Integer age;

    private List<Integer> skillIds; // To hold the skill IDs
    private List<String> skillNames; // To hold the skill names



    //GETTER AND SETTERS

    public boolean isProfileVisible() {
        return isProfileVisible;
    }

    public void setProfileVisible(boolean isProfileVisible) {
        this.isProfileVisible = isProfileVisible;
    }


    public List<Integer> getSkillIds() {
        return skillIds;
    }


    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }


    public List<String> getSkillNames() {
        return skillNames;
    }

    public void setSkillNames(List<String> skillNames) {
        this.skillNames = skillNames;
    }

    public void setIncludeProjects(boolean includeProjects) {
        this.includeProjects = includeProjects;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }
    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
