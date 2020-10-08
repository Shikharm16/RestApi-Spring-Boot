package com.employee.memberinfo.model.customvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class EmailConstraintValidator implements ConstraintValidator<IsEmail,String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		if(value.contains("@") && value.contains(".com") && value.endsWith(".com")) {
			return true;
		}
		return false;
	}

}
