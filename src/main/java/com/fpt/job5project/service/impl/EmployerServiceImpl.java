package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.EmployerDTO;
import com.fpt.job5project.entity.Employer;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.EmployerMapper;
import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.service.IEmployerService;
import com.fpt.job5project.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
//Autowired, private, final
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployerServiceImpl implements IEmployerService {

    EmployerRepository employerRepository;

    EmployerMapper employerMapper;

    IStorageService storageService;

    @Override
    public List<EmployerDTO> listOfEmployers() {
        List<EmployerDTO> listDTOs = new ArrayList<>();
        List<Employer> listEntities = employerRepository.findAll();
        if (listEntities.isEmpty()) {
            throw new AppException(ErrorCode.LIST_EMPLOYERS_IS_NULL);
        }
        for (Employer employerEntity : employerRepository.findAll()) {
            listDTOs.add(employerMapper.toDTO(employerEntity));
        }
        return listDTOs;
    }

    @Override
    public EmployerDTO getEmployer(long id) {
        Employer employers = employerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYER_NOT_EXIST));
        return employerMapper.toDTO(employers);
    }

    @Override
    public EmployerDTO addEmployer(EmployerDTO employerDTO) {
        Employer employerEntity = employerMapper.toEntity(employerDTO);
        Employer foundEmployer = employerRepository.findByEmployerName(employerEntity.getEmployerName());
        if (foundEmployer != null) {
            throw new AppException(ErrorCode.EMPLOYER_EXISTED);
        }
        return employerMapper.toDTO(employerRepository.save(employerEntity));
    }

    @Override
    public EmployerDTO updateEmployer(long id, EmployerDTO employerDTO, MultipartFile filePhoto, MultipartFile fileBackground) {
        if (employerRepository.existsByEmailAndEmployerIdNot(employerDTO.getEmail(), id))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        Employer foundEmployer = employerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYER_NOT_EXIST));

        employerMapper.updateEmployer(foundEmployer, employerDTO);

        if (!filePhoto.isEmpty()) {
            String generatedFilePhotoName = storageService.storeFile(filePhoto);
            foundEmployer.setPhoto(generatedFilePhotoName);
        }

        if (!fileBackground.isEmpty()) {
            String generatedFileBackgroundName = storageService.storeFile(fileBackground);
            foundEmployer.setBackGround(generatedFileBackgroundName);
        }

        return employerMapper.toDTO(employerRepository.save(foundEmployer));
    }

    @Override
    public void deleteEmployer(long id) {
        boolean existsById = employerRepository.existsById(id);
        if (!existsById) {
            throw new AppException(ErrorCode.EMPLOYER_NOT_EXIST);
        } else {
            employerRepository.deleteById(id);
        }
    }
}
