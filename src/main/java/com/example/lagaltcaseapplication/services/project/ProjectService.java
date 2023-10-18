package com.example.lagaltcaseapplication.services.project;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.models.Comment;

import java.util.List;


public interface ProjectService {
    ProjectDTO getProjectById(Long id);
    ProjectDTO createProject(ProjectDTO projectDTO);

    List<ProjectDTO> getAllProjects();

    List<ProjectDTO> getProjectsByUserId(Long userId);

    ProjectDTO updateProject(Long id, ProjectDTO updatedProjectDTO);

    void deleteProject(Long id);

    List<ProjectDTO> getProjectsByIndustry(Industry industry);

    void addCommentToProject(Long projectId, CommentDTO commentDTO);

    void addParticipantToProject(Long projectId, Long userId);

    void removeParticipant(Long projectId, Long userId);

}
