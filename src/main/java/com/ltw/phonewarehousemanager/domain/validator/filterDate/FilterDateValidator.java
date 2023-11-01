package com.ltw.phonewarehousemanager.domain.validator.filterDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterDateValidator implements ConstraintValidator<CheckFilterDate, String>{

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }
        Pattern p = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");
        Matcher m = p.matcher(s);
        boolean b = m.find();
        if(b){
            return true;
        }else {
            return false;
        }
    }
}
