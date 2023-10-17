package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.enums.Skills;
import com.example.lagaltcaseapplication.exceptions.ProjectNotFoundException;
import com.example.lagaltcaseapplication.mapper.ProjectMapper;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import com.example.lagaltcaseapplication.services.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMapper projectMapper;


    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            ProjectDTO projectDTO = projectMapper.toDTO(optionalProject.get());
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> allProjects = projectService.getAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, updatedProjectDTO);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/projects/industry/{industry}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByIndustry(@PathVariable String industry) {
        try {
            Industry enumIndustry = Industry.valueOf(industry.toUpperCase());
            List<ProjectDTO> projectDTOs = projectService.getProjectsByIndustry(enumIndustry);
            if (projectDTOs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projectDTOs, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/skills")
    public ResponseEntity<List<String>> getAllSkills() {
        List<String> skills = Arrays.stream(Skills.values())
                .map(Skills::name)
                .collect(Collectors.toList());
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/industries")
    public ResponseEntity<List<String>> getAllIndustries() {
        List<String> industries = Arrays.stream(Industry.values())
                .map(Industry::name)
                .collect(Collectors.toList());
        return new ResponseEntity<>(industries, HttpStatus.OK);
    }


}





