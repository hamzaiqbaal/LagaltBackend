package com.example.lagaltcaseapplication.services.project;

import com.example.lagaltcaseapplication.dto.ProjectDTO;

import java.util.List;


public interface ProjectService {
    ProjectDTO getProjectById(Long id);
    ProjectDTO createProject(ProjectDTO projectDTO);

    List<ProjectDTO> getAllProjects();

    List<ProjectDTO> getProjectsByUserId(Long userId);

    ProjectDTO updateProject(Long id, ProjectDTO updatedProjectDTO);

    void deleteProject(Long id);

    List<ProjectDTO> getProjectsBySkill(Long skillId);




}
