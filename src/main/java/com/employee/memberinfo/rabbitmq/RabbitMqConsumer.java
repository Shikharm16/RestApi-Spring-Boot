package com.employee.memberinfo.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class RabbitMqConsumer {
		
	@RabbitListener(queues="#{queue2.name}")
	public void listner2(String message) {
		recieve(message + " recieved through black queue");
	}
	
	@RabbitHandler
	public void recieve(String message) {
		System.out.println("Recieved = " + message);
	}

}
