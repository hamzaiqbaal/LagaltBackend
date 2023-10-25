package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.WorkApplicationDTO;
import com.example.lagaltcaseapplication.services.workapplication.WorkApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all work applications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all applications successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "No applications found", content = @Content)
    })
    public List<WorkApplicationDTO> getAllApplications() {
        return workApplicationService.getAllApplications();
    }

    @PostMapping("/")
    @Operation(summary = "Create a new work application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Application successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    })
    public WorkApplicationDTO createWorkApplication(@RequestBody WorkApplicationDTO workApplicationDTO) {
        return workApplicationService.createWorkApplication(workApplicationDTO);
    }

    @PostMapping("/accept/{applicationId}")
    @Operation(summary = "Accept a work application by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully accepted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Application not found", content = @Content)
    })
    public ResponseEntity<Void> acceptApplication(@PathVariable Long applicationId) {
        workApplicationService.acceptApplication(applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{applicationId}")
    @Operation(summary = "Delete a work application by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Application successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Application not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationId) {
        try {
            workApplicationService.deleteApplication(applicationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{applicationId}")
    @Operation(summary = "Update a work application by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Application successfully updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Application not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<WorkApplicationDTO> updateApplication(@PathVariable Long applicationId, @RequestBody WorkApplicationDTO workApplicationDTO) {
        try {
            WorkApplicationDTO updatedWorkApplicationDTO = workApplicationService.updateApplication(applicationId, workApplicationDTO);
            return new ResponseEntity<>(updatedWorkApplicationDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}