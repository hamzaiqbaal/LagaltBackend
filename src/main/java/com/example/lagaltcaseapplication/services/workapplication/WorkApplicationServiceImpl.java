package com.example.lagaltcaseapplication.services.workapplication;

import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.mapper.WorkApplicationMapper;
import com.example.lagaltcaseapplication.models.WorkApplication;
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
    private WorkApplicationMapper workApplicationMapper; // Assume you have a mapper

    @Override
    public List<WorkApplicationDTO> getAllApplications() {
        List<WorkApplication> workApplications = workApplicationRepository.findAll();
        return workApplications.stream()
                .map(workApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
