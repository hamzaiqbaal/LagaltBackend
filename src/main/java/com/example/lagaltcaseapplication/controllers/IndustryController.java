package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.IndustryDTO;
import com.example.lagaltcaseapplication.services.industry.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/industries")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    @GetMapping("/{id}")
    public ResponseEntity<IndustryDTO> getIndustryById(@PathVariable Long id) {
        IndustryDTO industryDTO = industryService.getIndustryById(id);
        return new ResponseEntity<>(industryDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IndustryDTO>> getAllIndustries() {
        List<IndustryDTO> industryDTOS = industryService.getAllIndustries();
        return new ResponseEntity<>(industryDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IndustryDTO> createIndustry(@RequestBody IndustryDTO industryDTO) {
        IndustryDTO createdIndustry = industryService.createIndustry(industryDTO);
        return new ResponseEntity<>(createdIndustry, HttpStatus.CREATED);
    }
}

