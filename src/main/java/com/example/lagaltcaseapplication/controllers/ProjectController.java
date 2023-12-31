package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.enums.Skills;
import com.example.lagaltcaseapplication.exceptions.ProjectNotFoundException;
import com.example.lagaltcaseapplication.exceptions.ResourceNotFoundException;
import com.example.lagaltcaseapplication.mapper.ProjectMapper;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import com.example.lagaltcaseapplication.services.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project found"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        try {
            ProjectDTO projectDTO = projectService.getProjectById(id);
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Create a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProject.getProjectId())
                .toUri();
        return ResponseEntity.created(location).body(createdProject);
    }

    @GetMapping("/")
    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects found"),
            @ApiResponse(responseCode = "204", description = "No projects found")
    })
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> allProjects = projectService.getAllProjects();
        if (allProjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project updated"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO updatedProjectDTO) {
        try {
            ProjectDTO updatedProject = projectService.updateProject(id, updatedProjectDTO);
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a project by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Project deleted"),
            @ApiResponse(responseCode = "404", description = "Project not found")
    })
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //OTHER METHODS

    @GetMapping("/projects/industry/{industry}")
    @Operation(summary = "Get projects by industry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects found"),
            @ApiResponse(responseCode = "204", description = "No projects found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
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
    @Operation(summary = "Get all skills")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skills found"),
            @ApiResponse(responseCode = "204", description = "No skills found")
    })
    public ResponseEntity<List<Map<String, Object>>> getAllSkills() {
        List<Map<String, Object>> skills = Stream.of(Skills.values())
                .map(skill -> {
                    Map<String, Object> skillMap = new HashMap<>();
                    skillMap.put("id", skill.getId());
                    skillMap.put("name", skill.getName());
                    return skillMap;
                })
                .collect(Collectors.toList());
        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping("/industries")
    @Operation(summary = "Get all industries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Industries found"),
            @ApiResponse(responseCode = "204", description = "No industries found")
    })
    public ResponseEntity<List<Map<String, Object>>> getAllIndustries() {
        List<Map<String, Object>> industries = Stream.of(Industry.values())
                .map(industry -> {
                    Map<String, Object> industryMap = new HashMap<>();
                    industryMap.put("id", industry.getId());
                    industryMap.put("name", industry.getName());
                    return industryMap;
                })
                .collect(Collectors.toList());
        if (industries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(industries, HttpStatus.OK);
    }

    @PostMapping("/{projectId}/addParticipant/{userId}")
    @Operation(summary = "Add participant to a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Participant added"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<Void> addParticipant(@PathVariable Long projectId, @PathVariable Long userId) {
        try {
            projectService.addParticipantToProject(projectId, userId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/projects/{projectId}/participants/{userId}")
    @Operation(summary = "Remove participant from a project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Participant removed"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    public ResponseEntity<Void> removeParticipant(@PathVariable Long projectId, @PathVariable Long userId) {
        try {
            projectService.removeParticipant(projectId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}






