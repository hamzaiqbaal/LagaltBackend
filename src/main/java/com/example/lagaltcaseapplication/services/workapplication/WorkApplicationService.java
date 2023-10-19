package com.example.lagaltcaseapplication.services.workapplication;

import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;

import java.util.List;

public interface WorkApplicationService {

    List<WorkApplicationDTO> getAllApplications();

    WorkApplicationDTO createWorkApplication(WorkApplicationDTO workApplicationDTO);

    void acceptApplication(Long applicationId);

}
