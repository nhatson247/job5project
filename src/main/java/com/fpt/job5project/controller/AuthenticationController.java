package com.fpt.job5project.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.AuthenticationDTO;
import com.fpt.job5project.dto.IntrospectDTO;
import com.fpt.job5project.dto.LogoutDTO;
import com.fpt.job5project.dto.RefreshDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.dto.UserDTO;
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

    @PostMapping("/register")
    ResponseObject<UserDTO> createUser(@ModelAttribute @Valid UserDTO request) {
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
                .build();
    }

}
