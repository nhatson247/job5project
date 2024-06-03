package com.fpt.job5project.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fpt.job5project.dto.CandidateDTO;

public interface ICandidateService {
    public List<CandidateDTO> listOfCandidate();

    public CandidateDTO getCandidate(long id);

    public CandidateDTO updateCandidate(long id, CandidateDTO candidateDTO, MultipartFile file);

    public void deleteCandidate(long id);

    public List<CandidateDTO> findAllByIdIn(List<Long> ids);
}
