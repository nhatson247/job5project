package com.fpt.job5project.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.fpt.job5project.dto.AuthenticationDTO;

public class UserValidator implements ConstraintValidator<UserConstraint, AuthenticationDTO> {

    @Override
    public boolean isValid(AuthenticationDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        boolean userNameValid = value.getUserName() != null && !value.getUserName().trim().isEmpty();
        boolean passwordValid = value.getPassword() != null && !value.getPassword().trim().isEmpty();

        if (!userNameValid && !passwordValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("USERNAME_INVALID").addPropertyNode("userName")
                    .addConstraintViolation();
            context.buildConstraintViolationWithTemplate("PASSWORD_INVALID").addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }

        return userNameValid && passwordValid;
    }
}