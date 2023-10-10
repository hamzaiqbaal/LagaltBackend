package com.example.lagaltcaseapplication.services.industry;

import com.example.lagaltcaseapplication.dto.IndustryDTO;
import com.example.lagaltcaseapplication.exceptions.IndustryNotFoundException;
import com.example.lagaltcaseapplication.mapper.IndustryMapper;
import com.example.lagaltcaseapplication.models.Industry;
import com.example.lagaltcaseapplication.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private IndustryMapper industryMapper;

    @Override
    public IndustryDTO getIndustryById(Long id) {
        Industry industry = industryRepository.findById(id)
                .orElseThrow(() -> new IndustryNotFoundException(id));
        return industryMapper.toDTO(industry);
    }

    @Override
    public List<IndustryDTO> getAllIndustries() {
        List<Industry> industries = industryRepository.findAll();
        return industries.stream()
                .map(industryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IndustryDTO createIndustry(IndustryDTO industryDTO) {
        Industry industry = industryMapper.toEntity(industryDTO);
        industry = industryRepository.save(industry);
        return industryMapper.toDTO(industry);
    }
}

