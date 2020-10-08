package com.employee.memberinfo.rabbitmq;


import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.employee.memberinfo.model.Employee;

public class Producer {

	@Autowired
	RabbitTemplate template;
	
	@Autowired
	DirectExchange direct;
	
	public void send() {
		template.convertAndSend(direct.getName(),"Black","Message from Sender/producer");
		System.out.println("Sent from sender side.");
	}

	public String sendpostemployee(Employee employee) {
		
		System.out.println("Sent from sender side for post employee.");
		return (String) template.convertSendAndReceive(direct.getName(), "Red", employee);
//		template.convertAndSend(direct.getName(),"Red",emp);
		
	}

}
