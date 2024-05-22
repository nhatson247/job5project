package com.fpt.job5project.mapper;


import com.fpt.job5project.dto.CVDTO;
import com.fpt.job5project.dto.CandidateDTO;
import com.fpt.job5project.entity.CV;
import com.fpt.job5project.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CVMapper {
    CVDTO toDTO(CV cv);
    CV toEntity(CVDTO cvdto);
    @Mapping(target = "cvId", ignore = true)
    @Mapping(target = "cvFile", ignore = true)
    void updateCV(@MappingTarget CV cv, CVDTO cvdto);
}
