package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectMapper {

    @Autowired
    private UserRepository userRepository;

    public ProjectDTO toDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProjectId(project.getProjectId());
        projectDTO.setTitle(project.getTitle());
        projectDTO.setDescription(project.getDescription());
        projectDTO.setStatus(project.getStatus());
        projectDTO.setOwnerUserId(project.getOwner().getUserId());
        projectDTO.setIndustry(project.getIndustry());
        projectDTO.setSkillsRequired(project.getSkillsRequired());
        return projectDTO;
    }
    public Project toEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectId(projectDTO.getProjectId());
        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());

        Long ownerUserId = projectDTO.getOwnerUserId();
        User owner = userRepository.findById(ownerUserId)
                .orElseThrow(() -> new UserNotFoundException(ownerUserId));
        project.setOwner(owner);
        project.setIndustry(projectDTO.getIndustry());
        project.setSkillsRequired(projectDTO.getSkillsRequired());
        return project;
    }
}

