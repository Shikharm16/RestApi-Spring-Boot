package com.employee.memberinfo.Redis;

import java.util.Map;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;

public interface RedisRepo {

	public void setEmployeeById(final String id, EmployeeGetDTO employeeDto);
	public EmployeeGetDTO findEmployeeById(final String id);
	public Map<String, EmployeeGetDTO> findAllEmployees();
	public void employeeDelete(String id);
}
