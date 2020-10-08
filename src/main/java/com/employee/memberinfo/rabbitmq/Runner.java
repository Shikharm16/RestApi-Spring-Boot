package com.employee.memberinfo.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	RabbitTemplate template;
		
	@Autowired 
	Producer produce;
	
	@Autowired
	ConfigurableApplicationContext ctx;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Starting Rabbitmq Communication...");
//		produce.send();

	}

}
