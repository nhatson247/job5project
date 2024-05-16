package com.fpt.job5project.mapper;


import com.fpt.job5project.dto.EmployerReviewDTO;
import com.fpt.job5project.entity.EmployerReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployerReviewMapper {
    EmployerReviewDTO toDTO(EmployerReview employerReview);
    EmployerReview toEntity(EmployerReviewDTO employerReviewDTO);
    @Mapping(target = "reviewId", ignore = true)
    void updateEmployerReview(@MappingTarget EmployerReview employerReview, EmployerReviewDTO employerReviewDTO);
}
