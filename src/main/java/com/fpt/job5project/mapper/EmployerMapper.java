package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.EmployerApprovedDTO;
import com.fpt.job5project.dto.EmployerDTO;
import com.fpt.job5project.entity.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployerMapper {
    EmployerDTO toDTO(Employer employer);

    Employer toEntity(EmployerDTO employerDTO);

    @Mapping(target = "employerId", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "background", ignore = true)
    void updateEmployer(@MappingTarget Employer employer, EmployerDTO employerDTO);

    EmployerApprovedDTO toDTOApprovedDTO(Employer employer);
}
