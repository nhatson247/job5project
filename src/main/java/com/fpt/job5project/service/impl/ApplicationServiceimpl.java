package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.ApplicationDTO;
import com.fpt.job5project.entity.Application;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.ApplicationMapper;
import com.fpt.job5project.repository.ApplicationRepository;
import com.fpt.job5project.service.IApplicationService;
import com.fpt.job5project.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationServiceimpl implements IApplicationService {
    ApplicationRepository applicationRepository;
    ApplicationMapper applicationMapper;
    IStorageService storageService;

    @Override
    public List<ApplicationDTO> listOfApplication() {
        List<ApplicationDTO> listDTOs = new ArrayList<>();
        List<Application> listEntities = applicationRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CV_IS_NULL);
        }
        for (Application applicationEntity : listEntities) {
            listDTOs.add(applicationMapper.toDTO(applicationEntity));
        }
        return listDTOs;
    }

    @Override
    public List<ApplicationDTO> listOfApplicationByJobId(Long id) {
        List<ApplicationDTO> listDTOs = new ArrayList<>();
        List<Application> listEntities = applicationRepository.findByJobIdOrderByApplicationIdDesc(id);
//        if (listEntities.isEmpty()) {
//            throw new AppException(ErrorCode.LIST_CV_IS_NULL);
//        }
        for (Application applicationEntity : listEntities) {
            listDTOs.add(applicationMapper.toDTO(applicationEntity));
        }
        return listDTOs;
    }

    @Override
    public ApplicationDTO updateApplicationStatus(long id, ApplicationDTO applicationDTO) {
        Application foundApplication = applicationRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        if (foundApplication != null) {
            applicationMapper.updateApplicationStatus(foundApplication, applicationDTO);
        }
        return applicationMapper.toDTO(applicationRepository.save(foundApplication));
    }

    @Override
    public List<ApplicationDTO> listOfApplicationByCandidateId(Long id) {
        List<ApplicationDTO> listDTOs = new ArrayList<>();
        List<Application> listEntities = applicationRepository.findByCandidateId(id);
        for (Application applicationEntity : listEntities) {
            listDTOs.add(applicationMapper.toDTO(applicationEntity));
        }
        return listDTOs;

    }

    @Override
    public ApplicationDTO getApplicationByCandidateAndJob(Long candidateId, Long jobId) {
        Application application = applicationRepository.findByCandidateIdAndJobId(candidateId,jobId);
//                .orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        return applicationMapper.toDTO(application);
    }


    @Override
    public ApplicationDTO addApplication(ApplicationDTO applicationDTO, MultipartFile file) {
        Application application = applicationMapper.toEntity(applicationDTO);
        String generatedFileName = storageService.storeFile(file);
        application.setCv(generatedFileName);
        application.setApplicationDate(new Date());
        return applicationMapper.toDTO(applicationRepository.save(application));
    }

    @Override
    public ApplicationDTO updateApplication(long id, ApplicationDTO applicationDTO, MultipartFile file) {
        Application foundApplication = applicationRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CV_NOT_EXIST));
        if (foundApplication != null) {
            applicationMapper.updateApplication(foundApplication, applicationDTO);
            if (!file.isEmpty()) {
                String generatedFileName = storageService.storeFile(file);
                foundApplication.setCv(generatedFileName);
            }
        }
        return applicationMapper.toDTO(applicationRepository.save(foundApplication));
    }

    @Override
    public void deleteApplication(long id) {
        boolean existsById = applicationRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.CV_NOT_EXIST);
        } else {
            applicationRepository.deleteById(id);
        }
    }
}
