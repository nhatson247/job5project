package com.fpt.job5project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    UNEXPECTED_ERROR(999, "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(400, "Invalid message key", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(400, "Invalid token", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(401, "Token expired", HttpStatus.UNAUTHORIZED),
    // Category error code

    CATEGORY_EXISTED(401, "Category already existed", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_NOT_NULL(402, "Category name can't be null", HttpStatus.BAD_REQUEST),
    LIST_CATEGORY_IS_NULL(403, "List of categories are empty", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXIST(404, "Category doesn't exist", HttpStatus.NOT_FOUND),
    // Industry error Code
    INDUSTRY_EXISTED(501, "Industry already existed", HttpStatus.BAD_REQUEST),
    // File error Code
    IMAGE_NULL(600, "Failed to store empty file", HttpStatus.BAD_REQUEST),
    FILE_DOES_NOT_ACEPPT(601, "You can only upload aceppted file", HttpStatus.BAD_REQUEST),
    FILE_CAPATICY_TOO_BIG(602, "File must be <= 5Mb", HttpStatus.BAD_REQUEST),
    STORE_FILE_WRONG_PLACE(603, "Cannot store file outside current directiory", HttpStatus.BAD_REQUEST),
    COULD_NOT_FIND_FILE(604, "Could not find file", HttpStatus.NOT_FOUND),
    COULD_NOT_READ_FILE(604, "Could not read file", HttpStatus.BAD_REQUEST),

    // User error code
    USER_INVALID(400, "User invalid", HttpStatus.BAD_REQUEST),
    PASSWORD_INCORRECT(402, "Password incorrect", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH(402, "Password mismatch", HttpStatus.BAD_REQUEST),
    USER_EXISTED(401, "User already existed", HttpStatus.BAD_REQUEST),

    USERNAME_NOT_NULL(701, "Username can't be null", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_NULL(702, "Password can't be null", HttpStatus.BAD_REQUEST),
    ROLE_NOT_NULL(703, "Role can't be null", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(704, "Username already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(705, "User doesn't exist", HttpStatus.NOT_FOUND),

    EMAIL_NOT_NULL(705, "Email can't be null", HttpStatus.BAD_REQUEST),
    PHONE_NOT_NULL(706, "Phone can't be null", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(705, "Email already existed", HttpStatus.BAD_REQUEST),

    // Employer error code
    EMPLOYER_EXISTED(801, "Employer already existed", HttpStatus.BAD_REQUEST),
    EMPLOYER_NAME_NOT_NULL(802, "Employer name can't be null", HttpStatus.BAD_REQUEST),
    LIST_EMPLOYERS_IS_NULL(803, "List of employers are empty", HttpStatus.BAD_REQUEST),
    EMPLOYER_NOT_EXIST(804, "Employer doesn't exist", HttpStatus.BAD_REQUEST),

    // Candidate error Code
    CANDIDATE_EXISTED(901, "Candidate already existed", HttpStatus.BAD_REQUEST),
    LIST_CANDIDATE_IS_NULL(903, "List of Candidates are empty", HttpStatus.BAD_REQUEST),
    CANDIDATE_NOT_EXIST(904, "Candidate doesn't exist", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),

    LIST_PROVINCES_IS_NULL(403, "List of provinces are empty", HttpStatus.BAD_REQUEST),

    LIST_JOB_IS_NULL(403, "List of jobs are empty", HttpStatus.BAD_REQUEST),
    LIST_JOBDescription_IS_NULL(403, "List of Job description are empty", HttpStatus.BAD_REQUEST),
    LIST_JOBBENEFIT_IS_NULL(403, "List of job benefit are empty", HttpStatus.BAD_REQUEST),
    LIST_JOBRequirement_IS_NULL(403, "List of job requirement are empty", HttpStatus.BAD_REQUEST),
    LIST_JOBType_IS_NULL(403, "List of job type are empty", HttpStatus.BAD_REQUEST),
    LIST_Industry_IS_NULL(403, "List of industry are empty", HttpStatus.BAD_REQUEST),
    JOB_NOT_EXIST(404, "Job doesn't exist", HttpStatus.BAD_REQUEST),
    JOBBENEFIT_NOT_EXIST(404, "Job benefit doesn't exist", HttpStatus.BAD_REQUEST),
    JOBDescription_NOT_EXIST(404, "Job description doesn't exist", HttpStatus.BAD_REQUEST),
    JOBRequirement_NOT_EXIST(404, "Job requirement doesn't exist", HttpStatus.BAD_REQUEST),
    JOBType_NOT_EXIST(404, "Job type doesn't exist", HttpStatus.BAD_REQUEST),

    LIST_RANKS_IS_NULL(403, "List of ranks are empty", HttpStatus.BAD_REQUEST),

    EMPLOYER_NOT_FOUND(800, "Employer not found", HttpStatus.NOT_FOUND),
    RANK_NOT_EXIST(404, "Rank doesn't exist", HttpStatus.BAD_REQUEST),
    EMPLOYER_NOT_APPROVED(801, "Employer not approved", HttpStatus.BAD_REQUEST),
    EMPLOYER_ALREADY_APPROVED(801, "Employer is alredy already approved", HttpStatus.BAD_REQUEST),
    //Job interest error code
    LIST_CV_IS_NULL(201, "List of job interests are empty",HttpStatus.BAD_REQUEST),
    CV_NOT_EXIST(204, "job interest doesn't exist",HttpStatus.BAD_REQUEST),

    //Job interest error code
    LIST_JOB_REPORT_IS_NULL(301, "List of job interests are empty",HttpStatus.BAD_REQUEST),
    JOB_REPORT_NOT_EXIST(304, "job interest doesn't exist",HttpStatus.BAD_REQUEST),

    //Job interest error code
    LIST_JOB_INTEREST_IS_NULL(401, "List of job interests are empty",HttpStatus.BAD_REQUEST),
    JOB_INTEREST_NOT_EXIST(404, "job interest doesn't exist",HttpStatus.BAD_REQUEST),
    ;

    private int status;
    private String message;
    private HttpStatusCode statusCode;
}
