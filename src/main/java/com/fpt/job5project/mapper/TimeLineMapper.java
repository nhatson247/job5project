package com.fpt.job5project.mapper;


import com.fpt.job5project.dto.TimeLineDTO;
import com.fpt.job5project.entity.TimeLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TimeLineMapper {
    TimeLineDTO toDTO(TimeLine timeLine);
    TimeLine toEntity(TimeLineDTO timeLineDTO);
    @Mapping(target = "timelineId", ignore = true)
    void updateTimeLine(@MappingTarget TimeLine timeLine, TimeLineDTO timeLineDTO);
}
