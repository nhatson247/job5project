package com.fpt.job5project.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.fpt.job5project.dto.ResponseObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    // thông báo lỗi server
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseObject<Void>> handlingRuntimeException(RuntimeException exception) {
        ResponseObject<Void> apiResponse = new ResponseObject<>();

        apiResponse.setStatus(ErrorCode.UNEXPECTED_ERROR.getStatus());
        apiResponse.setMessage(ErrorCode.UNEXPECTED_ERROR.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    // Xử lý ngoại lệ tự định nghĩa
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseObject<Void>> handlingAppException(AppException exception) {

        ErrorCode errorCode = exception.getErrorCode();

        ResponseObject<Void> responseObject = new ResponseObject<>();

        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(responseObject);
    }

    // Xử lý quyền truy cập
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ResponseObject<Void>> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ResponseObject.<Void>builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    ResponseEntity<ResponseObject<Void>> handlingEntityTooLarge(MaxUploadSizeExceededException exception) {

        ResponseObject<Void> responseObject = new ResponseObject<>();
        ErrorCode errorCode = ErrorCode.FILE_CAPATICY_TOO_BIG;
        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(responseObject);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject<List<String>>> handlingValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        ResponseObject<List<String>> responseObject = new ResponseObject<>();
        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());
        responseObject.setData(errors);

        return ResponseEntity.badRequest().body(responseObject);
    }

}
