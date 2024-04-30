package com.fpt.job5project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fpt.job5project.dto.UserChangeDTO;
import com.fpt.job5project.dto.UserDTO;
import com.fpt.job5project.entity.User;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.mapper.UserMapper;

import com.fpt.job5project.repository.UserRepository;
import com.fpt.job5project.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> listOfUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    // TO DO: Check tài khoản có đúng quyền truy cập không
    @PostAuthorize("returnObject.userName == authentication.name or hasAuthority('ROLE_Admin')")
    @Override
    public UserDTO getUserID(long id) {
        return userMapper.toUserDTO(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    // TO DO: Gọi thông tin tài khoản chính chủ
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
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
        userRepository.deleteById(id);
    }

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

        if (user.isBlocked()) {
            throw new AppException(ErrorCode.USER_ALREADY_LOCKED);
        }

        user.setBlocked(true);
        userMapper.toUserDTO(userRepository.save(user));
    }

    private boolean checkAccount(long userId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUsername = authentication.getName();

        // Admin có quyền truy cập
        if (authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Admin"))) {
            return true;
        }

        // Người dùng chỉ có thể chỉnh sửa chính mình
        User currentUser = userRepository.findByUserName(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return currentUser.getUserId() == userId;
    }

}
