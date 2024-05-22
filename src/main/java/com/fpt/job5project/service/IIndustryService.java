package com.fpt.job5project.service;

import java.util.List;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.dto.JobDTO;

public interface IIndustryService {
    public List<IndustryDTO> getAllIndustry();
    public IndustryDTO addIndustry(IndustryDTO newIndustryDTO);

    public IndustryDTO updateIndustry(long id, IndustryDTO newIndustryDTO);

    public void deleteIndustry(long id);
    public IndustryDTO getIndustryID(long id);
}
