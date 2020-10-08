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
import com.employee.memberinfo.model.Employee;
import com.employee.memberinfo.rabbitmq.Producer;

@Component
@Service
public class EmployeeService implements EmployeeDAO {

	@Autowired
	EmployeeRepo employeerepo;
	
	@Autowired
	Producer produce;
	
	@Autowired
	MappingDTO mappingdto;
	
	
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
		return mappingdto.EntityToDTO(employeerepo.findById(id));
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
	public void updatedetails(EmployeePostDTO employeeDto, String id) {
		
		checkIdForException(id);

		String errormessage=null;
		Employee employee=new Employee();
		Optional<Employee> retrive = fetchemployeebyid(id);
		
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
		
		produce.sendpostemployee(employee);

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
