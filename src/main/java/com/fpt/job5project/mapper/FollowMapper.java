package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.FollowDTO;
import com.fpt.job5project.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    FollowDTO toDTO(Follow follow);
    Follow toEntity(FollowDTO followDTO);
    @Mapping(target = "followId", ignore = true)
    void updateJobFollow(@MappingTarget Follow follow, FollowDTO followDTO);
}
