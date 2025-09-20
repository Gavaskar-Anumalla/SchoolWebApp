package com.eazybytes.eazyschool.validations;

import com.eazybytes.eazyschool.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch,Object> {

    private String field;
    private String fieldMatch;
    private String message;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
       this.field=constraintAnnotation.field();
       this.fieldMatch=constraintAnnotation.fieldMatch();
       this.message= constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Object fieldValue=new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue=new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}
