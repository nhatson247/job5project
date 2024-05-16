package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.JobInterestDTO;
import com.fpt.job5project.entity.JobInterest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobInterestMapper {
    JobInterestDTO toDTO(JobInterest jobInterest);
    JobInterest toEntity(JobInterestDTO jobInterestDTO);
}
