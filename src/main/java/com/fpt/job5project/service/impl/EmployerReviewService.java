package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.EmployerReviewDTO;
import com.fpt.job5project.entity.EmployerReview;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.EmployerReviewMapper;
import com.fpt.job5project.repository.EmployerReviewRepository;
import com.fpt.job5project.service.IEmployerReviewService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployerReviewService implements IEmployerReviewService {

    EmployerReviewRepository employerReviewRepository;
    EmployerReviewMapper employerReviewMapper;

    @Override
    public List<EmployerReviewDTO> listOfEmployerReview() {
        List<EmployerReviewDTO> listDTOs = new ArrayList<>();
        List<EmployerReview> listEntities = employerReviewRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CV_IS_NULL);
        }
        for (EmployerReview employerReviewEntity : employerReviewRepository.findAll()) {
            listDTOs.add(employerReviewMapper.toDTO(employerReviewEntity));
        }
        return listDTOs;
    }

    @Override
    public EmployerReviewDTO getEmployerReview(long id) {
        EmployerReview employerReview = employerReviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        return employerReviewMapper.toDTO(employerReview);
    }

    @Override
    public EmployerReviewDTO addEmployerReview(EmployerReviewDTO employerReviewDTO) {
        EmployerReview employerReview = employerReviewMapper.toEntity(employerReviewDTO);
        employerReview.setReviewDate(new Date());
        return employerReviewMapper.toDTO(employerReviewRepository.save(employerReview));
    }

    @Override
    public EmployerReviewDTO updateEmployerReview(long id, EmployerReviewDTO employerReviewDTO) {
        EmployerReview foundEmployerReview = employerReviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        if (foundEmployerReview != null) {
            employerReviewMapper.updateEmployerReview(foundEmployerReview, employerReviewDTO);
        }
        return employerReviewMapper.toDTO(employerReviewRepository.save(foundEmployerReview));
    }

    @Override
    public void deleteEmployerReview(long id) {
        boolean existsById = employerReviewRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.CV_NOT_EXIST);
        } else {
            employerReviewRepository.deleteById(id);
        }
    }
}
