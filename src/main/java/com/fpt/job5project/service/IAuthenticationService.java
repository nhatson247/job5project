package com.fpt.job5project.service;

import java.text.ParseException;

import com.fpt.job5project.dto.AuthenticationDTO;
import com.fpt.job5project.dto.IntrospectDTO;
import com.fpt.job5project.entity.User;
import com.nimbusds.jose.JOSEException;

public interface IAuthenticationService {
    public AuthenticationDTO authenticate(AuthenticationDTO request);

    public String generateToken(User user);

    public IntrospectDTO introspect(IntrospectDTO request) throws JOSEException, ParseException;
}
