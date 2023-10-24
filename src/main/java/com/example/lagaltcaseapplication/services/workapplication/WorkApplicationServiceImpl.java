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
        WorkApplication workApplication = workApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        workApplication.setAccepted(true);

        Project project = workApplication.getProject();

        project.getParticipants().add(workApplication.getUser());

        project.getWorkApplications().remove(workApplication);

        projectRepository.save(project);

        workApplicationRepository.delete(workApplication);
    }

    @Override
    public void deleteApplication(Long applicationId) {
        WorkApplication workApplication = workApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        workApplicationRepository.delete(workApplication);
    }

    @Override
    public WorkApplicationDTO updateApplication(Long applicationId, WorkApplicationDTO workApplicationDTO) {
        WorkApplication existingWorkApplication = workApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        existingWorkApplication.setAccepted(workApplicationDTO.isAccepted()); // Note the change here
        existingWorkApplication.setMotivation(workApplicationDTO.getMotivation());
        // ... update other fields if needed

        WorkApplication updatedWorkApplication = workApplicationRepository.save(existingWorkApplication);
        return workApplicationMapper.toDTO(updatedWorkApplication);
    }





}
