package com.employee.memberinfo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.employee.memberinfo.model.Employee;

@Controller
public class KafkaSend {
	
	@Autowired
	private KafkaTemplate<String,String> kafkatemplate;

	@Autowired
	private KafkaTemplate<String,Employee> kafkaemployeetemplate;
	
	public void sendMessage(Employee emp) {
		
	    kafkaemployeetemplate.send("emp-save", emp);
	    System.out.println("Message 1 Sent");
	}

	
	public void sendMessage2(String message) {
        
	    ListenableFuture<SendResult<String, String>> future = 
	      kafkatemplate.send("Trial1", message);
		
	    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
	 
	        @Override
	        public void onSuccess(SendResult<String, String> result) {
	            System.out.println("Sent message 2=[" + message +"] with offset=[" + result.getRecordMetadata().offset() + "]");
	        }
	        @Override
	        public void onFailure(Throwable ex) {
	            System.out.println("Unable to send message 2=["+ message + "] due to : " + ex.getMessage());
	        }
	    });
	}
	
}
