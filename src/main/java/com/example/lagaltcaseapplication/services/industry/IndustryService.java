package com.example.lagaltcaseapplication.services.industry;

import com.example.lagaltcaseapplication.dto.IndustryDTO;

import java.util.List;

public interface IndustryService {
    IndustryDTO getIndustryById(Long id);
    List<IndustryDTO> getAllIndustries();
    IndustryDTO createIndustry(IndustryDTO industryDTO);
}
