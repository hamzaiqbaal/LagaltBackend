package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.CommentDTO;
import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.models.Comment;
import com.example.lagaltcaseapplication.models.WorkApplication;
import org.springframework.stereotype.Component;

@Component
public class WorkApplicationMapper {

    public WorkApplication toEntity(WorkApplicationDTO workApplicationDTO) {
        WorkApplication workApplication = new WorkApplication();

        return workApplication;
    }

    public WorkApplicationDTO toDTO(WorkApplication workApplication) {
        WorkApplicationDTO workApplicationDTO = new WorkApplicationDTO();

        return workApplicationDTO;
    }
}