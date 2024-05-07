package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.JobBenefitDTO;
import com.fpt.job5project.entity.JobBenefit;

@Mapper(componentModel = "spring")

public interface JobBenefitMapper {
    JobBenefitDTO toDTO(JobBenefit jobBenefit);

    JobBenefit toEntity(JobBenefitDTO jobBenefitDTO);

    @Mapping(target = "benefitId", ignore = true)
    @Mapping(target = "jobId", ignore = true)
    void updateJobBenefit(@MappingTarget JobBenefit jobBenefit, JobBenefitDTO jobBenefitDTO);
}
