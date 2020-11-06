package com.employee.memberinfo.Controller.EmployeeDTO;


import org.springframework.stereotype.Component;

import com.employee.memberinfo.model.Employee;

@Component
public class MappingDTO {
	
	
	public Employee ConvertDTOtoEntity(EmployeePostDTO employeeDto) {
		Employee employee=new Employee();
		employee.setAge(employeeDto.getAge());
		employee.setEmail(employeeDto.getEmail());
		employee.setFirstName(employeeDto.getName());
		employee.setLastName(employeeDto.getSurname());

		return employee;
	}

	public EmployeeGetDTO EntityToDTO(Employee employee) {
		EmployeeGetDTO employeegetdto=new EmployeeGetDTO();
		employeegetdto.setAge(employee.getAge());
		employeegetdto.setName(employee.getFirstName());
		employeegetdto.setSurname(employee.getLastName());
		
		return employeegetdto;	
	}
}
