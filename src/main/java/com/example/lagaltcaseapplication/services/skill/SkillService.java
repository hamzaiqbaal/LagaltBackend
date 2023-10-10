package com.example.lagaltcaseapplication.services.skill;

import com.example.lagaltcaseapplication.dto.SkillDTO;

import java.util.List;

public interface SkillService {
    List<SkillDTO> getAllSkills();
    SkillDTO getSkillById(Long id);
    SkillDTO createSkill(SkillDTO skillDTO);

    List<SkillDTO> getSkillsByUserId(Long userId);

}

