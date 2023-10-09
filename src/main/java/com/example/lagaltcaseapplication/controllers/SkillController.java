package com.example.lagaltcaseapplication.controllers;

import com.example.lagaltcaseapplication.dto.SkillDTO;
import com.example.lagaltcaseapplication.services.skill.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @GetMapping("/")
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.getSkillById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SkillDTO> createSkill(@RequestBody SkillDTO skillDTO) {
        return new ResponseEntity<>(skillService.createSkill(skillDTO), HttpStatus.CREATED);
    }
}
