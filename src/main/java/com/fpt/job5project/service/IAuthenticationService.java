package com.fpt.job5project.service;

import java.text.ParseException;

import com.fpt.job5project.dto.AuthenticationDTO;
import com.fpt.job5project.dto.IntrospectDTO;
import com.fpt.job5project.dto.LogoutDTO;
import com.fpt.job5project.dto.RefreshDTO;
import com.fpt.job5project.entity.User;
import com.nimbusds.jose.JOSEException;

public interface IAuthenticationService {
    public AuthenticationDTO authenticate(AuthenticationDTO request);

    public String generateToken(User user);

    public IntrospectDTO introspect(IntrospectDTO request) throws JOSEException, ParseException;

    public void logout(LogoutDTO request) throws JOSEException, ParseException;

    public AuthenticationDTO refreshToken(RefreshDTO request) throws ParseException, JOSEException;
}
