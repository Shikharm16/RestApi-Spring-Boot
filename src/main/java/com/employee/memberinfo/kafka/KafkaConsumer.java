package com.employee.memberinfo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.employee.memberinfo.model.Employee;
import com.employee.memberinfo.service.EmployeeService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class KafkaConsumer {

	@Autowired
	EmployeeService service;
	
	@KafkaListener(topics = "Trial1", groupId = "foo")
	public void listenGroupTrial(String message) {
	    System.out.println("Received Message in group foo: " + message);
	}
	
	@KafkaListener(topics = "emp-save", groupId = "foo",
			  containerFactory = "KafkaListenerContainerFactoryForEmployee")
	public void listenEmployee(Employee employee) {
		
		service.saveemployee(employee);
	    System.out.println("Employee saved");
	}

}
//ObjectMapper mapper = new ObjectMapper();
//try {
//	Employee employee=mapper.readValue(emp, Employee.class);
//	service.saveemployee(employee);
//} catch (JsonMappingException e) {
//	e.printStackTrace();
//} catch (JsonProcessingException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}