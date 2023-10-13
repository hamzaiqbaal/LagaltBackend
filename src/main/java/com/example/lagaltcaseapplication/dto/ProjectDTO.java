package com.example.lagaltcaseapplication.dto;

import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.enums.Skills;

import java.util.Set;

public class ProjectDTO {
    private Long projectId;
    private String title;
    private String description;
    private String status;
    private Long ownerUserId;
    private String ownerName;

    private Industry industry;
    private Set<Skills> skillsRequired;

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

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public Set<Skills> getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(Set<Skills> skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

