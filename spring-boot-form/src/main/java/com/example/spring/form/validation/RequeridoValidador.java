package com.example.spring.form.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String>{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        /*if(value == null || value.isEmpty() || value.isBlank()){
            return false;
        }*/
        if(value == null || !StringUtils.hasText(value)){ //Si el value es null o no tiene texto...
            return false;
        }
            return true;
    }
}
