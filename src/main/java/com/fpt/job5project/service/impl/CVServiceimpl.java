package com.fpt.job5project.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fpt.job5project.dto.CVDTO;
import com.fpt.job5project.entity.CV;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.CVMapper;
import com.fpt.job5project.repository.CVRepository;
import com.fpt.job5project.service.ICVService;
import com.fpt.job5project.service.IStorageService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CVServiceimpl implements ICVService {
    CVRepository cvRepository;
    CVMapper cvMapper;
    IStorageService storageService;

    @Override
    public List<CVDTO> listOfCv(Long id) {
        List<CVDTO> listDTOs = new ArrayList<>();
        List<CV> listEntities = cvRepository.findByCandidateId(id);
        // if (listEntities.isEmpty()) {
        // throw new AppException(ErrorCode.LIST_CV_IS_NULL);
        // }
        // listEntities.sort((cv1, cv2) -> cv2.isMainCV().compareTo(cv1.isMainCV()));
        for (CV cvEntity : cvRepository.findByCandidateId(id)) {
            listDTOs.add(cvMapper.toDTO(cvEntity));
        }
        return listDTOs;
    }

    @Override
    public CVDTO getCv(long id) {
        CV cv = cvRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        return cvMapper.toDTO(cv);
    }

    @Override
    public CVDTO addCv(CVDTO cvdto, MultipartFile file) {
        CV cv = cvMapper.toEntity(cvdto);
        String generatedFileName = storageService.storeFile(file);
        cv.setCvFile(generatedFileName);
        return cvMapper.toDTO(cvRepository.save(cv));
    }

    @Override
    public CVDTO updateCv(long id, CVDTO cvdto, MultipartFile file) {
        CV foundCV = cvRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        if (foundCV != null) {
            cvMapper.updateCV(foundCV, cvdto);
            if (!file.isEmpty()) {
                String generatedFileName = storageService.storeFile(file);
                foundCV.setCvFile(generatedFileName);
            }
        }
        return cvMapper.toDTO(cvRepository.save(foundCV));
    }

    @Override
    public void deleteCv(long id) {
        boolean existsById = cvRepository.existsById(id);
        // if (!existsById) {
        // throw new AppException(ErrorCode.CV_NOT_EXIST);
        // } else {
        // }
        cvRepository.deleteById(id);
    }
}
