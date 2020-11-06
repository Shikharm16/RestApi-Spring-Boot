package com.employee.memberinfo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
	
	static final String queuename1="Red";
	static final String queuename2="Black";
	
	@Bean
	public DirectExchange direct() {
		return new DirectExchange("two-queue-direct");
	}
	
	@SuppressWarnings("unused")
	private static class recieverconf {
		
		@Bean
		public Queue queue1() {
			return new Queue("Red-One", true);
		}
		
		@Bean
		public Queue queue2() {
			return new Queue("Black-one",true);
		}
		
		
		@Bean
		public Binding bindWithQueue1(Queue queue1 , DirectExchange direct) {
			return BindingBuilder.bind(queue1).to(direct).with(queuename1);
		}
		
		@Bean
		public Binding bindWithQueue2(Queue queue2 , DirectExchange direct) {
			return BindingBuilder.bind(queue2).to(direct).with(queuename2);
		}
		
		@Bean
		public RabbitMqConsumer reciever() {
			return new RabbitMqConsumer();
		}
		
		@Bean
		public RabbitEmployeePostReciever postreciever() {
			return new RabbitEmployeePostReciever();
		}
	}
	
	@Bean
	public RabbitMqProducer produce() {
		return new RabbitMqProducer();
	}
	
//	  @Bean
//	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter) {
//		  
//	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//	    container.setConnectionFactory(connectionFactory);
//	    container.setQueueNames(queueName);
//	    container.setMessageListener(listenerAdapter);
//	    return container;
//	  }
//
//	  @Bean
//	  MessageListenerAdapter listenerAdapter(Reciever receiver) {
//	    return new MessageListenerAdapter(receiver, "receiveMessage");
//	  }
}
