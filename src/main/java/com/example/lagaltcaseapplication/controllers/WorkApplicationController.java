package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.services.workapplication.WorkApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public WorkApplicationDTO createWorkApplication(@RequestBody WorkApplicationDTO workApplicationDTO) {
        return workApplicationService.createWorkApplication(workApplicationDTO);
    }

    @PostMapping("/accept/{applicationId}")
    public ResponseEntity<Void> acceptApplication(@PathVariable Long applicationId) {
        workApplicationService.acceptApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}