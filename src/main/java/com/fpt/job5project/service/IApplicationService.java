package com.fpt.job5project.service;

import com.fpt.job5project.dto.ApplicationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IApplicationService {
    public List<ApplicationDTO> listOfApplication();

    public ApplicationDTO addApplication(ApplicationDTO applicationDTO, MultipartFile file);

    public ApplicationDTO updateApplication(long id, ApplicationDTO applicationDTO, MultipartFile file);

    public void deleteApplication(long id);

    public List<ApplicationDTO> listOfApplicationByJobId(Long id);

    public ApplicationDTO updateApplicationStatus(long id, ApplicationDTO applicationDTO);
    public List<ApplicationDTO> listOfApplicationByCandidateId(Long id);

    public ApplicationDTO getApplicationByCandidateAndJob(Long candidateId, Long jobId);
}
