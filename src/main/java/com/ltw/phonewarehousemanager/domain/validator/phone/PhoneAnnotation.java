package com.ltw.phonewarehousemanager.domain.validator.phone;

import com.ltw.phonewarehousemanager.constant.Constants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneAnnotation {
    String message() default Constants.ErrorMessageSupplierValidation.CHECK_PHONE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
