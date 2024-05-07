package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.JobDTO;
import com.fpt.job5project.entity.Job;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobDTO toDTO(Job job);

    Job toEntity(JobDTO jobDTO);

    @Mapping(target = "jobId", ignore = true)
    // @Mapping(target = "photo", ignore = true)
    void updateJob(@MappingTarget Job job, JobDTO jobDTO);

}
