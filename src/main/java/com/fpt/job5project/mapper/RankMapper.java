package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.RankDTO;
import com.fpt.job5project.entity.Rank;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RankMapper {
    RankDTO toDTO(Rank Rank);
    Rank toEntity(RankDTO RankDTO);
}
