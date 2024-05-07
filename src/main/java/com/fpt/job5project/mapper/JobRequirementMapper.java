package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.JobRequirementDTO;
import com.fpt.job5project.entity.JobRequirement;

@Mapper(componentModel = "spring")
public interface JobRequirementMapper {
    JobRequirementDTO toDTO(JobRequirement jobRequirement);

    JobRequirement toEntity(JobRequirementDTO jobRequirementDTO);

    @Mapping(target = "requirementId", ignore = true)
    @Mapping(target = "jobId", ignore = true)
    void updateJobRequirement(@MappingTarget JobRequirement jobRequirement, JobRequirementDTO jobRequirementDTO);
}
