package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.CandidateDTO;
import com.fpt.job5project.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CandiadteMapper {
    CandidateDTO toDTO(Candidate candidate);
    Candidate toEntity(CandidateDTO candidateDTO);
    @Mapping(target = "candidateId", ignore = true)
    @Mapping(target = "photo", ignore = true)
    void updateCandidate(@MappingTarget Candidate candidate, CandidateDTO candidateDTO);

}
