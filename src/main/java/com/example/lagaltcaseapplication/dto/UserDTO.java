package com.example.lagaltcaseapplication.dto;

import com.example.lagaltcaseapplication.enums.Skills;

import java.util.List;
import java.util.Set;

public class UserDTO {

    private Long userId;
    private String forName;
    private String lastName;
    private String description;
    private String country;
    private String email;
    private String userRole;
    private List<ProjectDTO> projects;


    // Getter and Setter methods
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

    private Set<Skills> skills;

    public Set<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

}
