package com.employee.memberinfo.Controller.EmployeeDTO;


import org.springframework.stereotype.Component;

import com.employee.memberinfo.model.Employee;

@Component
public class MappingDTO {
	
	
	public Employee ConvertDTOtoEntity(EmployeePostDTO employeeDto) {
		Employee emp=new Employee();
		emp.setAge(employeeDto.getAge());
		emp.setEmail(employeeDto.getEmail());
		emp.setFirstName(employeeDto.getName());
		emp.setLastName(employeeDto.getSurname());

		return emp;
	}

	public EmployeeGetDTO EntityToDTO(Employee employee) {
		EmployeeGetDTO employeegetdto=new EmployeeGetDTO();
		employeegetdto.setAge(employee.getAge());
		employeegetdto.setName(employee.getFirstName());
		employeegetdto.setSurname(employee.getLastName());
		
		return employeegetdto;	
	}
}
