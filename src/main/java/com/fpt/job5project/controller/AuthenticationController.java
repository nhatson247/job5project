package com.fpt.job5project.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.job5project.dto.AuthenticationDTO;
import com.fpt.job5project.dto.IntrospectDTO;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.service.IAuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @PostMapping("/token")
    ResponseObject<AuthenticationDTO> authenticate(@ModelAttribute AuthenticationDTO request) {
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
}
