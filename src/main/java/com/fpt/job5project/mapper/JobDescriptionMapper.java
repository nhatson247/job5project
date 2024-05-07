package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.JobDescriptionDTO;
import com.fpt.job5project.entity.JobDescription;

@Mapper(componentModel = "spring")
public interface JobDescriptionMapper {
    JobDescriptionDTO toDTO(JobDescription jobDescription);

    JobDescription toEntity(JobDescriptionDTO jobDescriptionDTO);

    @Mapping(target = "descriptionId", ignore = true)
    @Mapping(target = "jobId", ignore = true)
    void updateJobDescription(@MappingTarget JobDescription jobDescription, JobDescriptionDTO jobDescriptionDTO);
}
