package com.example.lagaltcaseapplication.services.skill;

import com.example.lagaltcaseapplication.dto.SkillDTO;
import com.example.lagaltcaseapplication.exceptions.SkillNotFoundException;
import com.example.lagaltcaseapplication.mapper.SkillMapper;
import com.example.lagaltcaseapplication.models.Skill;
import com.example.lagaltcaseapplication.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Override
    public List<SkillDTO> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream().map(SkillMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(() -> new SkillNotFoundException("Skill not found"));
        return skillMapper.toDTO(skill);
    }

    @Override
    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = skillMapper.toEntity(skillDTO);
        Skill savedSkill = skillRepository.save(skill);
        return skillMapper.toDTO(savedSkill);
    }

    @Override
    public List<SkillDTO> getSkillsByUserId(Long userId) {
        List<Skill> skills = skillRepository.findSkillsByUserId(userId);
        return skills.stream().map(SkillMapper::toDTO).collect(Collectors.toList());
    }
}

