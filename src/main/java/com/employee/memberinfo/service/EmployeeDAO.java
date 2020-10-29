package com.employee.memberinfo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.BindingResult;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;
import com.employee.memberinfo.Controller.EmployeeDTO.EmployeePostDTO;
import com.employee.memberinfo.model.Employee;

public interface EmployeeDAO {

	public List<Employee> fetchemployee();
	public List<EmployeeGetDTO> fetchAllDtoEmployee();
	public Optional<Employee> fetchemployeebyid(String id);
	public EmployeeGetDTO fetchDtoemployeebyid(String id);
	public void deleteemployeebyid(String id);
	public void addEmployeeToRabbit(EmployeePostDTO employeeDto, BindingResult bindingresult);
	public void addEmployeeToKafka(EmployeePostDTO employeeDto, BindingResult bindingresult);
	public void saveemployee(Employee employee);
	public EmployeeGetDTO updatedetails(EmployeePostDTO employeeDto, String id);
	public void checkForValidationErrors(BindingResult bindingresult);
	public void checkIdForException(String id);
}
