package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.services.workapplication.WorkApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workapplications")
public class WorkApplicationController {

    @Autowired
    private WorkApplicationService workApplicationService;

    @GetMapping("/")
    public List<WorkApplicationDTO> getAllApplications() {
        return workApplicationService.getAllApplications();
    }

}
