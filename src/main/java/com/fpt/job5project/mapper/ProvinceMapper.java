package com.fpt.job5project.mapper;

import com.fpt.job5project.dto.ProvinceDTO;
import com.fpt.job5project.entity.Province;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ProvinceMapper {
    ProvinceDTO toDTO(Province Province);
    Province toEntity(ProvinceDTO ProvinceDTO);
}
