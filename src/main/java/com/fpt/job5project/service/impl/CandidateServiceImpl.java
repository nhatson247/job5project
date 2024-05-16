package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.CandidateDTO;
import com.fpt.job5project.entity.Candidate;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.CandiadteMapper;
import com.fpt.job5project.repository.CandidateRepository;
import com.fpt.job5project.service.ICandidateService;
import com.fpt.job5project.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateServiceImpl implements ICandidateService {
    CandidateRepository candidateRepository;
    CandiadteMapper candiadteMapper;
    IStorageService storageService;

    @Override
    public List<CandidateDTO> listOfCandidate() {
        List<CandidateDTO> listDTOs = new ArrayList<>();
        List<Candidate> listEntities = candidateRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CANDIDATE_IS_NULL);
        }
        for (Candidate candidateEntity : listEntities) {
            listDTOs.add(candiadteMapper.toDTO(candidateEntity));
        }
        return listDTOs;
    }

    @Override
    public CandidateDTO getCandidate(long id) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        return candiadteMapper.toDTO(candidate);
    }

    @Override
    public CandidateDTO updateCandidate(long id, CandidateDTO candidateDTO, MultipartFile file) {

        if (candidateRepository.existsByEmailAndCandidateIdNot(candidateDTO.getEmail(), id))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_NOT_EXIST));
        if (foundCandidate != null) {
            candiadteMapper.updateCandidate(foundCandidate, candidateDTO);
            String generatedFileName = storageService.storeFile(file);
            foundCandidate.setPhoto(generatedFileName);
        }
        return candiadteMapper.toDTO(candidateRepository.save(foundCandidate));
    }


    @Override
    public void deleteCandidate(long id) {
        boolean existsById = candidateRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.CANDIDATE_NOT_EXIST);
        } else {
            candidateRepository.deleteById(id);
        }
    }

    @Override
    public List<CandidateDTO> findAllByIdIn(List<Long> ids) {
        List<CandidateDTO> listDTOs = new ArrayList<>();
        List<Candidate> listEntities = candidateRepository.findAllByCandidateIdIn(ids);
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_CANDIDATE_IS_NULL);
        }
        for (Candidate candidateEntity : listEntities) {
            listDTOs.add(candiadteMapper.toDTO(candidateEntity));
        }
        return listDTOs;
    }


}
