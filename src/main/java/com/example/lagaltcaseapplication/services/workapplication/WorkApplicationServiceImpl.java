package com.example.lagaltcaseapplication.services.workapplication;

import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.mapper.WorkApplicationMapper;
import com.example.lagaltcaseapplication.models.Project;
import com.example.lagaltcaseapplication.models.WorkApplication;
import com.example.lagaltcaseapplication.repository.ProjectRepository;
import com.example.lagaltcaseapplication.repository.WorkApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkApplicationServiceImpl implements WorkApplicationService {

    @Autowired
    private WorkApplicationRepository workApplicationRepository;

    @Autowired
    private WorkApplicationMapper workApplicationMapper;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<WorkApplicationDTO> getAllApplications() {
        List<WorkApplication> workApplications = workApplicationRepository.findAll();
        return workApplications.stream()
                .map(workApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WorkApplicationDTO createWorkApplication(WorkApplicationDTO workApplicationDTO) {
        WorkApplication workApplication = workApplicationMapper.toEntity(workApplicationDTO);
        WorkApplication savedWorkApplication = workApplicationRepository.save(workApplication);
        return workApplicationMapper.toDTO(savedWorkApplication);
    }


    public void acceptApplication(Long applicationId) {
        // Fetch the work application
        WorkApplication workApplication = workApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        // Mark the application as accepted
        workApplication.setAccepted(true);
        workApplicationRepository.save(workApplication);

        // Fetch the related project
        Project project = workApplication.getProject();

        // Add the user to the project's participant list
        project.getParticipants().add(workApplication.getUser());
        projectRepository.save(project);
    }

}
