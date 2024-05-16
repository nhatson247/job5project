package com.fpt.job5project.service.impl;

import com.fpt.job5project.dto.EmployerApprovedDTO;
import com.fpt.job5project.dto.EmployerDTO;
import com.fpt.job5project.dto.MailDTO;
import com.fpt.job5project.entity.Employer;
import com.fpt.job5project.entity.User;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.EmployerMapper;
import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.repository.UserRepository;
import com.fpt.job5project.service.IEmployerService;
import com.fpt.job5project.service.IMailService;
import com.fpt.job5project.service.IStorageService;
import com.fpt.job5project.utils.Const;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//Autowired, private, final
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployerServiceImpl implements IEmployerService {

    EmployerRepository employerRepository;

    EmployerMapper employerMapper;

    IStorageService storageService;
    IMailService iMailService;

    UserRepository userRepository;

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
            foundEmployer.setBackground(generatedFileBackgroundName);
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

     @Override
    public int upRank(long rankId, long userId) {
        return employerRepository.updateRankById(rankId, userId);
    }

    @Override
    public List<EmployerApprovedDTO> listOfApprovedEmployers() {
        List<Employer> pendingEmployers = employerRepository.findByApprovedFalse();
        return pendingEmployers.stream()
                .map(employerMapper::toDTOApprovedDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void approveAcceptEmployer(long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYER_NOT_EXIST));

        if (employer.isApproved()) {
            throw new AppException(ErrorCode.EMPLOYER_ALREADY_APPROVED);
        }

        employer.setApproved(true);
        employerRepository.save(employer);

        sendApprovalEmail(employer);
    }

    private void sendApprovalEmail(Employer employer) {
        try {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setTo(employer.getEmail());
            mailDTO.setSubject(Const.SEND_MAIL_SUBJECT.SERVER_ACCEPT_EMPLOYER);
            Map<String, Object> props = new HashMap<>();
            props.put("employerName", employer.getEmployerName());
            mailDTO.setProps(props);

            iMailService.sendHtmlMail(mailDTO, Const.TEMPLATE_FILE_NAME.EMPLOYER_ACCEPT);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployerSendMail(long id) {

        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYER_NOT_EXIST));

        // Xóa tài khoản User liên kết với Employer
        User user = employer.getUser();
        userRepository.delete(user);

        // Xóa Employer
        employerRepository.delete(employer);

        // Gửi email thông báo từ chối cho Employer
        sendDeleteEmployerByEmail(employer);

    }

    private void sendDeleteEmployerByEmail(Employer employer) {
        try {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setTo(employer.getEmail());
            mailDTO.setSubject(Const.SEND_MAIL_SUBJECT.SERVER_NOT_ACCEPT_EMPLOYER);
            Map<String, Object> props = new HashMap<>();
            props.put("employerName", employer.getEmployerName());
            mailDTO.setProps(props);

            iMailService.sendHtmlMail(mailDTO, Const.TEMPLATE_FILE_NAME.EMPLOYER_DELETE);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
