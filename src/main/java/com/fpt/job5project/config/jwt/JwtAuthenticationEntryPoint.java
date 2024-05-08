package com.fpt.job5project.config.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.job5project.dto.ResponseObject;
import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {
        ErrorCode errorCode;

        if (authException.getMessage() != null &&
                authException.getMessage().contains("Token expired")) {
            errorCode = ErrorCode.TOKEN_EXPIRED;
        } else {
            errorCode = ErrorCode.UNAUTHENTICATED;
        }

        response.setStatus(errorCode.getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ResponseObject<?> responseObject = ResponseObject.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(responseObject));
        response.flushBuffer();
    }
}
