package com.employee.memberinfo.Exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomisedException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleallException(Exception ex , WebRequest request ){
		ExceptionResponseStructure exceptionresponse = new ExceptionResponseStructure(new Date()
				, ex.getMessage() , request.getDescription(false));
		return new ResponseEntity<Object>(exceptionresponse , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(EmployeePostException.class)
	public final ResponseEntity<Object> handleemployeenotfoundException(Exception ex , WebRequest request ){
		ExceptionResponseStructure exceptionresponse = new ExceptionResponseStructure(new Date()
				, ex.getMessage() , request.getDescription(false));
		return new ResponseEntity<Object>(exceptionresponse , HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(RequestNotFoundException.class)
	public final ResponseEntity<Object> requestnotfoundException(Exception ex , WebRequest request ){
		ExceptionResponseStructure exceptionresponse = new ExceptionResponseStructure(new Date()
				, ex.getMessage() , request.getDescription(false));
		return new ResponseEntity<Object>(exceptionresponse , HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(EmployeeWithIdNotFoundException.class)
	public final ResponseEntity<Object> EmployeeIdNotFound(Exception ex , WebRequest request ){
		ExceptionResponseStructure exceptionresponse = new ExceptionResponseStructure(new Date()
				, ex.getMessage() , request.getDescription(false));
		return new ResponseEntity<Object>(exceptionresponse , HttpStatus.BAD_REQUEST);
	}

	
}
