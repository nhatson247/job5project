package com.fpt.job5project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    UNEXPECTED_ERROR(999, "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(400, "Invalid message key", HttpStatus.BAD_REQUEST),
    // Category error code
    CATEGORY_EXISTED(401, "Category already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_NOT_NULL(402, "Category name can't be null", HttpStatus.BAD_REQUEST),

    // User error code
    PASSWORD_INCORRECT(402, "Password incorrect", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH(402, "Password mismatch", HttpStatus.BAD_REQUEST),
    USER_EXISTED(401, "User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    USER_INVALID(400, "User invalid", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    ;

    private int status;
    private String message;
    private HttpStatusCode statusCode;
}
