package com.fpt.job5project.mapper;


import com.fpt.job5project.dto.JobReportDTO;
import com.fpt.job5project.entity.JobReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobReportMapper {
    JobReportDTO toDTO(JobReport jobReport);
    JobReport toEntity(JobReportDTO jobReportDTO);
}
