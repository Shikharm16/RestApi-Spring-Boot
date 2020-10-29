package com.employee.memberinfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeePostDTO;
import com.employee.memberinfo.Exception.EmployeePostException;
import com.employee.memberinfo.model.Employee;

@Component
public class EmployeeUpdateCheck {

	@Autowired
	EmployeeService service;
	
	public Employee check(String id,EmployeePostDTO employeeDto) {
		
		String errormessage=null;
		Employee employee=new Employee();
		Optional<Employee> retrive = service.fetchemployeebyid(id);
		
		employee.setId(id);
		
		if(true) {
			if(employeeDto.getAge() == 0) {
				employee.setAge(retrive.get().getAge());
			}
			else if(employeeDto.getAge()>=20 && employeeDto.getAge()<=60) {
				employee.setAge(employeeDto.getAge());
			}
			else {
				errormessage=errormessage+" AGE ERROR : Age is not in the range 20 to 60";
			}
		}
		
		if(true) {
			if(employeeDto.getSurname() == null) {
				employee.setLastName(retrive.get().getLastName());
			}
			else if(employeeDto.getSurname() != null && employeeDto.getSurname().length()>=2) {
				employee.setLastName(employeeDto.getSurname());
			}
			else {
				errormessage=errormessage+" SURNAME ERROR";
			}
		}

		if(true) {
			if(employeeDto.getName() == null) {
				employee.setFirstName(retrive.get().getFirstName());
			}
			else if(employeeDto.getName() != null && employeeDto.getName().length()!=0) {
				employee.setFirstName(employeeDto.getName());
			}
			else {
				errormessage=errormessage+" NAME ERROR";
			}
		}
		
		if(true) {
			if(employeeDto.getEmail() == null) {
				employee.setEmail(retrive.get().getEmail());
			}
			else if(employeeDto.getEmail() != null && employeeDto.getEmail().contains("@")
					&& employeeDto.getEmail().contains(".com") && employeeDto.getEmail().endsWith(".com")) {
				
				employee.setEmail(employeeDto.getEmail());
			}
			else {
				errormessage=errormessage+" EMAIL ERROR";
			}
		}
		
		if(errormessage != null)
		{throw new EmployeePostException(errormessage);}
		
		return employee;

	}
}
