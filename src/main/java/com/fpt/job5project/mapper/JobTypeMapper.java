package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.JobTypeDTO;
import com.fpt.job5project.entity.JobType;

@Mapper(componentModel = "spring")
public interface JobTypeMapper {
    JobTypeDTO toDTO(JobType jobType);

    JobType toEntity(JobTypeDTO jobTypeDTO);

    @Mapping(target = "typeId", ignore = true)
    void updateJobType(@MappingTarget JobType jobType, JobTypeDTO jobTypeDTO);
}
