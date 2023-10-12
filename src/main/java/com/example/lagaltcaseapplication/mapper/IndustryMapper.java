package com.example.lagaltcaseapplication.mapper;

import com.example.lagaltcaseapplication.dto.IndustryDTO;
import com.example.lagaltcaseapplication.models.Industry;
import org.springframework.stereotype.Component;

@Component
public class IndustryMapper {

    public IndustryDTO toDTO(Industry industry) {
        IndustryDTO dto = new IndustryDTO();
        dto.setIndustryId(industry.getIndustryId());
        dto.setName(industry.getName());
        return dto;
    }

    public Industry toEntity(IndustryDTO dto) {
        Industry industry = new Industry();
        industry.setIndustryId(dto.getIndustryId());
        industry.setName(dto.getName());
        return industry;
    }
}

