package com.fpt.job5project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.entity.Industry;

@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryDTO toDTO(Industry Industry);

    Industry toEntity(IndustryDTO IndustryDTO);

    @Mapping(target = "industryId", ignore = true)
    // @Mapping(target = "photo", ignore = true)
    void updateIndustry(@MappingTarget Industry industry, IndustryDTO industryDTO);
}
