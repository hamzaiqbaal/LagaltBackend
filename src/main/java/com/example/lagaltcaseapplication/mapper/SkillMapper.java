package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.SkillDTO;
import com.example.lagaltcaseapplication.models.Skill;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {
    public Skill toEntity(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setSkillId(skillDTO.getSkillId());
        skill.setSkillName(skillDTO.getSkillName());
        return skill;
    }

    public SkillDTO toDTO(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkillId(skill.getSkillId());
        skillDTO.setSkillName(skill.getSkillName());
        return skillDTO;
    }
}
