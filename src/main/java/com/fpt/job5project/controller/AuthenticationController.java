package com.fpt.job5project.controller;

import java.text.ParseException;

import com.fpt.job5project.dto.*;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.repository.CandidateRepository;
import com.fpt.job5project.repository.EmployerRepository;
import org.springframework.web.bind.annotation.*;

import com.fpt.job5project.service.IAuthenticationService;
import com.fpt.job5project.service.IUserService;
import com.nimbusds.jose.JOSEException;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    IAuthenticationService authenticationService;
    EmployerRepository employerRepository;
    CandidateRepository candidateRepository;

    IUserService iUserService;

    @PostMapping("/login")
    ResponseObject<AuthenticationDTO> authenticate(@ModelAttribute @Valid AuthenticationDTO request) {
        var result = authenticationService.authenticate(request);
        return ResponseObject.<AuthenticationDTO>builder()
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    ResponseObject<IntrospectDTO> authenticate(@ModelAttribute IntrospectDTO request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ResponseObject.<IntrospectDTO>builder()
                .data(result)
                .build();
    }

    @PostMapping("/register/{email}")
    ResponseObject<UserDTO> createUser(@ModelAttribute @Valid UserDTO request, @PathVariable("email") String email) {
        if (candidateRepository.existsByEmail(email) || employerRepository.existsByEmail(email)) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        return ResponseObject.<UserDTO>builder()
                .data(iUserService.addUser(request))
                .build();
    }

    @PostMapping("/refresh")
    ResponseObject<AuthenticationDTO> authenticate(@ModelAttribute RefreshDTO request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ResponseObject.<AuthenticationDTO>builder()
                .data(result)
                .build();
    }

    @PostMapping("/logout")
    ResponseObject<Void> logout(@ModelAttribute LogoutDTO request) throws JOSEException, ParseException {
        authenticationService.logout(request);
        return ResponseObject.<Void>builder()
                .message("logout successful")
                .build();
    }

}
