package com.employee.memberinfo.Controller.EmployeeDTO;

import java.util.Optional;

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
		
	public EmployeeGetDTO EntityToDTO(Optional<Employee> employee) {
		EmployeeGetDTO employeegetdto=new EmployeeGetDTO();
		employeegetdto.setAge(employee.get().getAge());
		employeegetdto.setName(employee.get().getFirstName());
		employeegetdto.setSurname(employee.get().getLastName());
		
		return employeegetdto;
		
	}
}
