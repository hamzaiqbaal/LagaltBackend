package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lagaltcaseapplication.enums.Skills;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
            projectDTO.setOwnerName(project.getOwner().getForName());


            // Handle null Industry
            if (project.getIndustry() != null) {
                projectDTO.setIndustry(project.getIndustry().getName());
            } else {
                projectDTO.setIndustry(null);
            }

            // Handle null Skills
            if (project.getSkillsRequired() != null) {
                Set<String> skillsDisplayNames = project.getSkillsRequired().stream()
                        .map(Skills::getName)
                        .collect(Collectors.toSet());
                projectDTO.setSkillsRequired(skillsDisplayNames);
            } else {
                projectDTO.setSkillsRequired(null);
            }

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


            String industryFriendlyName = projectDTO.getIndustry();
            Industry industryEnum = Arrays.stream(Industry.values())
                    .filter(industry -> industry.getName().equals(industryFriendlyName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid industry name"));
            project.setIndustry(industryEnum);



            Set<Skills> skillsEnums = projectDTO.getSkillsRequired().stream()
                    .map(skillString -> Skills.valueOf(skillString.toUpperCase()))
                    .collect(Collectors.toSet());
            project.setSkillsRequired(skillsEnums);

            return project;
        }
    }

