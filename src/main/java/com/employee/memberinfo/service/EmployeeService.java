package com.employee.memberinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;
import com.employee.memberinfo.Controller.EmployeeDTO.EmployeePostDTO;
import com.employee.memberinfo.Controller.EmployeeDTO.MappingDTO;
import com.employee.memberinfo.Exception.EmployeePostException;
import com.employee.memberinfo.Exception.EmployeeWithIdNotFoundException;
import com.employee.memberinfo.Exception.RequestNotFoundException;
import com.employee.memberinfo.Repository.EmployeeRepo;
import com.employee.memberinfo.kafka.KafkaSend;
import com.employee.memberinfo.model.Employee;
import com.employee.memberinfo.rabbitmq.RabbitMqProducer;

@Component
@Service
public class EmployeeService implements EmployeeDAO {

	@Autowired
	EmployeeRepo employeerepo;
	
	@Autowired
	RabbitMqProducer produce;
	
	@Autowired
	KafkaSend kafkasend;
	
	@Autowired
	MappingDTO mappingdto;
	
	@Autowired
	EmployeeUpdateCheck employeeupdatecheck;
	
	
	@Override
	public List<Employee> fetchemployee() {
		return employeerepo.findAll();
	}
	
	@Override
	public List<EmployeeGetDTO> fetchAllDtoEmployee() {
		List<Employee> findAll = employeerepo.findAll();
		List<EmployeeGetDTO> list=new ArrayList<EmployeeGetDTO>();
		
		for (Employee employee:findAll) {
			
			EmployeeGetDTO dtoemployee=new EmployeeGetDTO();
			
			dtoemployee.setAge(employee.getAge());
			dtoemployee.setName(employee.getFirstName());
			dtoemployee.setSurname(employee.getLastName());
			
			list.add(dtoemployee);
		}
		return list;
	}
	
	@Override
	public Optional<Employee> fetchemployeebyid(String id) {
		
		checkIdForException(id);
		return employeerepo.findById(id);
	}
	
	@Override
	public EmployeeGetDTO fetchDtoemployeebyid(String id) {
		
		checkIdForException(id);
		return mappingdto.EntityToDTO(employeerepo.findById(id).get());
	}
	
	@Override
	public void deleteemployeebyid(String id) {
		
		checkIdForException(id);
		employeerepo.deleteById(id);
		return;
	}
	
	@Override
	public void addEmployeeToRabbit(EmployeePostDTO employeeDto, BindingResult bindingresult) {
		
		checkForValidationErrors(bindingresult);
		Employee employee=mappingdto.ConvertDTOtoEntity(employeeDto);
		produce.sendpostemployee(employee);
	}
	
	@Override
	public void addEmployeeToKafka(EmployeePostDTO employeeDto, BindingResult bindingresult) {
		
		checkForValidationErrors(bindingresult);
		Employee employee=mappingdto.ConvertDTOtoEntity(employeeDto);
		
		kafkasend.sendMessage(employee);
	}
	
	@Override
	public void saveemployee(Employee employee) {
		try 
		{ employeerepo.save(employee); }
		
		catch(Exception ex)
		{ throw new EmployeePostException("One or more fields incorrect:"
				+ " email (must have @ and .com) / age (between 20 to 60)/ Name (Can not be NULL)"
				+ " / LastName ( Must have more then 2 characters)");
		}
		return;
	}
	
	@Override
	public EmployeeGetDTO updatedetails(EmployeePostDTO employeeDto, String id) {
		
		checkIdForException(id);
		
		Employee employee = employeeupdatecheck.check(id,employeeDto);
		
		produce.sendpostemployee(employee);
		return mappingdto.EntityToDTO(employee);

	}
	
	@Override
	public void checkForValidationErrors(BindingResult bindingresult) {
		
		String errormessage="";
		
		if(bindingresult.hasErrors())
		{
			List<FieldError> errors = bindingresult.getFieldErrors();
			for (FieldError error : errors ) {
		        errormessage = errormessage + error.getObjectName() + " - " + error.getDefaultMessage() + " && ";
		    }
			throw new EmployeePostException(errormessage);
		}
	}
	
	@Override
	public void checkIdForException(String id) {
		if (id.length()<36) {
			throw new RequestNotFoundException("You made wrong Request.");
		}
		if(employeerepo.findById(id).isEmpty()) {
			throw new EmployeeWithIdNotFoundException("Employee with eid- " + id + " Not Found");
		}
	}
	
}
