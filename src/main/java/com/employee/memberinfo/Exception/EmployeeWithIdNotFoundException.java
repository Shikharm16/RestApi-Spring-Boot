package com.employee.memberinfo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeWithIdNotFoundException extends RuntimeException{

	public EmployeeWithIdNotFoundException(String message) {
		super(message);
	}
}
