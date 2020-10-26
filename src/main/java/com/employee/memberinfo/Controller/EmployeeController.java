package com.employee.memberinfo.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;
import com.employee.memberinfo.Controller.EmployeeDTO.EmployeePostDTO;
import com.employee.memberinfo.Exception.RequestNotFoundException;
import com.employee.memberinfo.service.EmployeeService;


@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("page")
	public String Getpage() {
		return "login";
	}
		
	@GetMapping("")
	public List<EmployeeGetDTO> fetchDtoEmployee() {
		
		return service.fetchAllDtoEmployee();
	}
	
	
	@Cacheable(value="employees",key="#id")
	@GetMapping("{id}")
	public EmployeeGetDTO GetEmployeeById(@PathVariable String id) {
				
		EmployeeGetDTO fetchDtoemployeebyid = service.fetchDtoemployeebyid(id);
		
		return fetchDtoemployeebyid;
	}
		
	@CacheEvict(value = "employees", allEntries=false)
	@DeleteMapping("{id}")
	public ResponseEntity<String> DeleteEmployeeById(@PathVariable String id) {
				
		service.deleteemployeebyid(id);

		return new ResponseEntity<String>("Employee Deleted!", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("")
	public ResponseEntity<String> AddEmployee(@Valid @RequestBody EmployeePostDTO employeeDto , BindingResult bindingresult) {
				
		service.addEmployeeToRabbit(employeeDto, bindingresult);
		
		return new ResponseEntity<String>("New Employee Created through rabbitmq!" , HttpStatus.CREATED);	
	}
	
	@PostMapping("kafka")
	public ResponseEntity<String> AddEmployeeKafka(@Valid @RequestBody EmployeePostDTO employeeDto , BindingResult bindingresult) {
				
		service.addEmployeeToKafka(employeeDto, bindingresult);
		
		return new ResponseEntity<String>("New Employee Created throuugh kafka!" , HttpStatus.CREATED);	
	}
	
	@CachePut(value = "employees", key = "#id")
	@PatchMapping("{id}")
	public EmployeeGetDTO UpdateEmployeeInfo(@RequestBody EmployeePostDTO employeeDto, @PathVariable String id)
	{		
		EmployeeGetDTO edto=service.updatedetails(employeeDto, id);

		return edto;
	}
	
	@RequestMapping("*")
	public void HandleAllOtherRequest() {
		
		throw new RequestNotFoundException("You made wrong Request.");
	}
	
}
