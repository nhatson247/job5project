package com.fpt.job5project.exception;

import com.fpt.job5project.dto.ResponseObject;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO thông báo lỗi server
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ResponseObject> handlingRuntimeException(RuntimeException exception) {
        ResponseObject apiResponse = new ResponseObject();

        apiResponse.setStatus(ErrorCode.UNEXPECTED_ERROR.getStatus());
        apiResponse.setMessage(ErrorCode.UNEXPECTED_ERROR.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    // TODO Xử lý ngoại lệ tự định nghĩa
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ResponseObject> handlingAppException(AppException exception) {

        ErrorCode errorCode = exception.getErrorCode();

        ResponseObject responseObject = new ResponseObject();

        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getStatusCode()).body(responseObject);
    }

    // TODO Xử lý quyền truy cập
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ResponseObject> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getStatusCode()).body(
                ResponseObject.builder()
                        .status(errorCode.getStatus())
                        .message(errorCode.getMessage())
                        .build());
    }

    // @ExceptionHandler(value = MethodArgumentNotValidException.class)
    // ResponseEntity<ResponseObject>
    // handlingValidation(MethodArgumentNotValidException exception) {

    // String enumKey = exception.getFieldError().getDefaultMessage();

    // ErrorCode errorCode = ErrorCode.INVALID_KEY;
    // try {
    // errorCode = ErrorCode.valueOf(enumKey);
    // } catch (IllegalArgumentException e) {
    // e.getMessage();
    // }

    // ResponseObject responseObject = new ResponseObject();

    // responseObject.setStatus(errorCode.getStatus());
    // responseObject.setMessage(errorCode.getMessage());

    // return ResponseEntity.badRequest().body(responseObject);
    // }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    ResponseEntity<ResponseObject> handlingEntityTooLarge(MaxUploadSizeExceededException exception) {

        ResponseObject responseObject = new ResponseObject();
        ErrorCode errorCode = ErrorCode.FILE_CAPATICY_TOO_BIG;
        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(responseObject);
    }

    // TODO thông báo lỗi từ các trường requested
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject> handlingValidation(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatus(errorCode.getStatus());
        responseObject.setMessage(errorCode.getMessage());
        responseObject.setData(errors);

        return ResponseEntity.badRequest().body(responseObject);
    }

}
