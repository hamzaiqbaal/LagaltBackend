package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.exceptions.ProjectNotFoundException;
import com.example.lagaltcaseapplication.exceptions.UserNotFoundException;
import com.example.lagaltcaseapplication.models.Comment;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.models.User;
import com.example.lagaltcaseapplication.models.WorkApplication;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import com.example.lagaltcaseapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkApplicationMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserMapper userMapper;

    public WorkApplication toEntity(WorkApplicationDTO workApplicationDTO) {
        WorkApplication workApplication = new WorkApplication();

        workApplication.setApplication_id(workApplicationDTO.getApplicationId());

        // Fetch Project and User entities from their repositories
        Project project = projectRepository.findById(workApplicationDTO.getProjectId())
                .orElseThrow(() -> new ProjectNotFoundException(workApplicationDTO.getProjectId()));
        User user = userRepository.findById(workApplicationDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(workApplicationDTO.getUserId()));

        workApplication.setProject(project);
        workApplication.setUser(user);
        workApplication.setAccepted(workApplicationDTO.isAccepted());
        workApplication.setMotivation(workApplicationDTO.getMotivation());

        return workApplication;
    }

    public WorkApplicationDTO toDTO(WorkApplication workApplication) {
        WorkApplicationDTO workApplicationDTO = new WorkApplicationDTO();

        workApplicationDTO.setApplicationId(workApplication.getApplication_id());
        workApplicationDTO.setProjectId(workApplication.getProject().getProjectId());
        workApplicationDTO.setUserId(workApplication.getUser().getUserId());
        workApplicationDTO.setAccepted(workApplication.isAccepted());
        workApplicationDTO.setMotivation(workApplication.getMotivation());

        workApplicationDTO.setForName(workApplication.getUser().getForName());

        return workApplicationDTO;
    }
}