package com.example.lagaltcaseapplication.dto;

import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.enums.Skills;

import java.util.List;
import java.util.Set;

public class ProjectDTO {
    private Long projectId;
    private String title;
    private String description;
    private String status;
    private Long ownerUserId;
    private String ownerName;

    private List<UserDTO> participants;

    private String industryName;
    private Set<String> skillsRequiredNames;

    // New fields for POST using IDs
    private Integer industryId;
    private List<Integer> skillsRequiredIds;

    public List<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDTO> participants) {
        this.participants = participants;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public Set<String> getSkillsRequiredNames() {
        return skillsRequiredNames;
    }

    public void setSkillsRequiredNames(Set<String> skillsRequiredNames) {
        this.skillsRequiredNames = skillsRequiredNames;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public List<Integer> getSkillsRequiredIds() {
        return skillsRequiredIds;
    }

    public void setSkillsRequiredIds(List<Integer> skillsRequiredIds) {
        this.skillsRequiredIds = skillsRequiredIds;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

