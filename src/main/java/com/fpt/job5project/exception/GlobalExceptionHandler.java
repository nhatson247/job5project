package com.fpt.job5project.exception;

import com.fpt.job5project.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseObject> handlingRuntimeException(RuntimeException exception) {
        ResponseObject apiResponse = new ResponseObject();

        apiResponse.setStatus(ErrorCode.UNEXPECTED_ERROR.getStatus());
        apiResponse.setMessage(ErrorCode.UNEXPECTED_ERROR.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseObject> handlingAppException(AppException exception) {

        ErrorCode errorCode = exception.getErrorCode();

        ResponseObject responseObject = new ResponseObject();

        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(responseObject);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ResponseObject> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ResponseObject.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ResponseObject> handlingValidation(MethodArgumentNotValidException exception) {

        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

        ResponseObject responseObject = new ResponseObject();

        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(responseObject);
    }
}
