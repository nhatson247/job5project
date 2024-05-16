package com.fpt.job5project.service;

import com.fpt.job5project.dto.EmployerApprovedDTO;
import com.fpt.job5project.dto.EmployerDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IEmployerService {
    public List<EmployerDTO> listOfEmployers();

    public EmployerDTO getEmployer(long id);

    public EmployerDTO addEmployer(EmployerDTO employerDTO);

    public EmployerDTO updateEmployer(long id, EmployerDTO employerDTO,MultipartFile photo, MultipartFile background);

    public void deleteEmployer(long id);

    public int upRank(long userId, long rankId);

    public List<EmployerApprovedDTO> listOfApprovedEmployers();

    public void approveAcceptEmployer(long id);

    public void deleteEmployerSendMail(long id);

}
