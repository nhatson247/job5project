package com.fpt.job5project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.job5project.dto.EmployerDTO;
import com.fpt.job5project.dto.ForgetPasswordDTO;
import com.fpt.job5project.dto.MailDTO;
import com.fpt.job5project.dto.UserChangeDTO;
import com.fpt.job5project.dto.UserDTO;
import com.fpt.job5project.entity.Candidate;
import com.fpt.job5project.entity.Employer;
import com.fpt.job5project.entity.User;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.UserMapper;
import com.fpt.job5project.repository.CandidateRepository;
import com.fpt.job5project.repository.EmployerRepository;
import com.fpt.job5project.repository.UserRepository;
import com.fpt.job5project.service.IMailService;
import com.fpt.job5project.service.IUserService;
import com.fpt.job5project.utils.Const;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IMailService iMailService;

    // TO DO: Danh sách User
    // Có lọc Employer được duyệt
    @Override
    public List<UserDTO> listOfUsers() {
        List<User> users = userRepository.findAllUsersFilteredByEmployerApproval();
        return users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    // TO DO: Danh sách User theo ID
    // Check tài khoản có đúng quyền truy cập không
    @PostAuthorize("returnObject.userName == authentication.name or hasAuthority('ROLE_admin')")
    @Override
    public UserDTO getUserID(long id) {
        return userMapper.toUserDTO(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    // TO DO: Gọi thông tin tài khoản chính chủ
    @Override
    public UserDTO getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUserName(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO addUser(UserDTO request) {

        if (userRepository.existsByUserName(request.getUserName()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        passwordEncoder = new BCryptPasswordEncoder(5);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(long id, UserDTO request) {

        if (!checkAccount(id))
            throw new AppException(ErrorCode.UNAUTHORIZED);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userRepository.delete(user);
    }

    @Override
    public void changePassword(long userId, UserChangeDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }

        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new AppException(ErrorCode.PASSWORD_MISMATCH);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void lockAccount(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean blockCrr = user.isBlocked();
        user.setBlocked(!blockCrr);
        userMapper.toUserDTO(userRepository.save(user));
    }

    private boolean checkAccount(long userId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUsername = authentication.getName();

        // admin có quyền truy cập
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_admin"))) {
            return true;
        }

        // Người dùng chỉ có thể chỉnh sửa chính mình
        User currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return currentUser.getUserId() == userId;
    }

    // quên mật khẩu
    @Override
    public void forgetPassword(ForgetPasswordDTO request) {

        Employer employer = employerRepository.findByEmail(request.getEmail());
        Candidate candidate = candidateRepository.findByEmail(request.getEmail());

        if (employer == null && candidate == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        User user = (employer != null) ? employer.getUser() : candidate.getUser();

        String newPassword = UUID.randomUUID().toString().substring(0, 8);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        String userName = (employer != null) ? employer.getEmployerName() : candidate.getFullName();
        String userEmail = request.getEmail();

        sendNewPasswordByEmail(userEmail, newPassword, userName);
    }

    private void sendNewPasswordByEmail(String userEmail, String newPassword, String name) {
        try {
            MailDTO mailDTO = new MailDTO();

            mailDTO.setTo(userEmail);
            mailDTO.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("userName", name);
            props.put("password", newPassword);
            mailDTO.setProps(props);

            iMailService.sendHtmlMail(mailDTO, Const.TEMPLATE_FILE_NAME.USER_REGISTER);
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
    }

}
