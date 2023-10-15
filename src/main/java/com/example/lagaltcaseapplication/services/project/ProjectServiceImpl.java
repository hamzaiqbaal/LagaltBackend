package com.example.lagaltcaseapplication.services.project;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.enums.Skills;
import com.example.lagaltcaseapplication.exceptions.ProjectNotFoundException;
import com.example.lagaltcaseapplication.mapper.ProjectMapper;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.lagaltcaseapplication.enums.Industry.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;



    @Override
    public ProjectDTO getProjectById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return projectMapper.toDTO(project);
        } else {
            throw new ProjectNotFoundException(id);
        }
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);

        project = projectRepository.save(project);
        return projectMapper.toDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getProjectsByUserId(Long userId) {
        List<Project> projects = projectRepository.findByOwner_UserId(userId);
        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO updatedProjectDTO) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (!optionalProject.isPresent()) {
            throw new ProjectNotFoundException(id);
        }

        Project existingProject = optionalProject.get();
        existingProject.setTitle(updatedProjectDTO.getTitle());
        existingProject.setDescription(updatedProjectDTO.getDescription());
        existingProject.setStatus(updatedProjectDTO.getStatus());

        Project updatedProject = projectRepository.save(existingProject);
        return projectMapper.toDTO(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(id);
        }
        projectRepository.deleteById(id);
    }


    @Override
    public List<ProjectDTO> getProjectsByIndustry(Industry industry) {
        List<Project> projects = projectRepository.findByIndustry(industry);
        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

}

