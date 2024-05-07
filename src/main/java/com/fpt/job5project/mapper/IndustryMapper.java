package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.IndustryDTO;
import com.fpt.job5project.entity.Industry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IndustryMapper {
    IndustryDTO toDTO(Industry Industry);
    Industry toEntity(IndustryDTO IndustryDTO);
}
