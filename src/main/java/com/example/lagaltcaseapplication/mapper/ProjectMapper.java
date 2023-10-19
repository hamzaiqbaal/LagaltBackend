package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.ProjectDTO;
import com.example.lagaltcaseapplication.dto.UserDTO;
import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.enums.Industry;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lagaltcaseapplication.enums.Skills;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
    public class ProjectMapper {

        @Autowired
        private UserRepository userRepository;


        @Autowired
        private UserMapper userMapper;

        @Autowired
        private WorkApplicationMapper workApplicationMapper;


    public ProjectDTO toDTO(Project project) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setProjectId(project.getProjectId());
            projectDTO.setTitle(project.getTitle());
            projectDTO.setDescription(project.getDescription());
            projectDTO.setStatus(project.getStatus());
            projectDTO.setOwnerUserId(project.getOwner().getUserId());
            projectDTO.setOwnerName(project.getOwner().getForName());

            List<UserDTO> participants = project.getParticipants().stream()
                    .map(userMapper::toSimplifiedDTO)  // Assuming you have added the toSimplifiedDTO method in UserMapper
                    .collect(Collectors.toList());
            projectDTO.setParticipants(participants);

            // Handle null Industry
            if (project.getIndustry() != null) {
                projectDTO.setIndustryId(project.getIndustry().getId());
                projectDTO.setIndustryName(project.getIndustry().getName());
            }

            if (project.getSkillsRequired() != null) {
                List<Integer> skillsIds = project.getSkillsRequired().stream()
                        .map(Skills::getId)
                        .collect(Collectors.toList());
                List<String> skillsNames = project.getSkillsRequired().stream()
                        .map(Skills::getName)
                        .collect(Collectors.toList());
                projectDTO.setSkillsRequiredIds(skillsIds);
                projectDTO.setSkillsRequiredNames(new HashSet<>(skillsNames));
            }

            List<WorkApplicationDTO> workApplicationDTOs = project.getWorkApplications().stream()
                    .map(workApplicationMapper::toDTO)
                    .collect(Collectors.toList());

            projectDTO.setWorkApplications(workApplicationDTOs);

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

        // Handle industry
        Integer industryId = projectDTO.getIndustryId();
        Industry industryEnum = Industry.getById(industryId);
        project.setIndustry(industryEnum);

        List<Integer> skillsIds = projectDTO.getSkillsRequiredIds();
        Set<Skills> skillsEnums = skillsIds.stream()
                .map(Skills::getById)
                .collect(Collectors.toSet());
        project.setSkillsRequired(skillsEnums);

        return project;
        }
    }

