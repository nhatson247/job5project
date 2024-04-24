package com.fpt.job5project.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AppException extends RuntimeException {
    private final ErrorCode errorCode;

}
