package com.employee.memberinfo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.employee.memberinfo.kafka.KafkaSend;
import com.employee.memberinfo.rabbitmq.RabbitMqProducer;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	RabbitTemplate template;
		
	@Autowired 
	RabbitMqProducer produce;
	 
	@Autowired
	KafkaSend kafkasend;
	
	@Autowired
	ConfigurableApplicationContext ctx;
		
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Starting Rabbitmq/kafka Communication...");
//		produce.send();

		kafkasend.sendMessage2("hello from kafka sender");
		
	}

}
