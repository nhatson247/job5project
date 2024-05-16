package com.fpt.job5project.mapper;


import com.fpt.job5project.dto.ApplicationDTO;
import com.fpt.job5project.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDTO toDTO(Application application);
    Application toEntity(ApplicationDTO applicationDTO);
    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "cv", ignore = true)
    void updateApplication(@MappingTarget Application application, ApplicationDTO applicationDTO);

    @Mapping(target = "applicationId", ignore = true)
    @Mapping(target = "cv", ignore = true)
    @Mapping(target = "jobId", ignore = true)
    @Mapping(target = "candidateId", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    void updateApplicationStatus(@MappingTarget Application application, ApplicationDTO applicationDTO);
}
