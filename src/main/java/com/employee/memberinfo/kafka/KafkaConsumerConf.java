package com.employee.memberinfo.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.employee.memberinfo.model.Employee;

@EnableKafka
@Configuration
public class KafkaConsumerConf {
 
	@Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
	    
    public ConsumerFactory<String, String> consumerFactory(String groupId) {
        
    	Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG , groupId );
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
        
    }
 
   
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
   
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId));
        return factory;
    }
       
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
        return kafkaListenerContainerFactory("foo");
    }
    
    public ConsumerFactory<String, Employee> consumerFactoryForEmployee(String groupId) {
       
    	Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG , groupId );
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),
        		new JsonDeserializer<>(Employee.class));
    }

   
    public ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactoryForEmployee(String groupId) {
   
        ConcurrentKafkaListenerContainerFactory<String, Employee> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForEmployee(groupId));
        return factory;
    }
       
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Employee> KafkaListenerContainerFactoryForEmployee() {
        return kafkaListenerContainerFactoryForEmployee("foo");
    }

}