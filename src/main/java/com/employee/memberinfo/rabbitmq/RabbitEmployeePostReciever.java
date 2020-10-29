package com.employee.memberinfo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.employee.memberinfo.model.Employee;
import com.employee.memberinfo.service.EmployeeService;

public class RabbitEmployeePostReciever {

	@Autowired
	EmployeeService service;
	
	@RabbitListener(queues="#{queue1.name}")
	public String PostNewEmployee(Employee employee) {
		
		System.out.println("Post employee through Red Queue-1");
		service.saveemployee(employee);
		System.out.println("Donee!! ");

		return "Done";
	}
}
