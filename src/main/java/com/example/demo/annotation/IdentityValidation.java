package com.example.demo.annotation;

import com.example.demo.annotation.declare.Identity;
import com.example.demo.util.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IdentityValidation implements ConstraintValidator<Identity, String> {
    private boolean required = false;
    private Pattern pattern = Pattern.compile("^\\d{15}|\\d{}18$ ");
    @Override
    public void initialize(Identity constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(required) {
            return pattern.matcher(value).matches();
        }else {
            if(ValidateUtil.isEmpty(value)) {
                return false;
            }else{
                return pattern.matcher(value).matches();
            }
        }
    }
}
