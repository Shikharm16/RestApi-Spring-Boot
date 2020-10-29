package com.employee.memberinfo.Redis;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;
@Service
public class RedisService implements RedisRepo{

	private final String EMPLOYEE_CACHE = "employee";
	 
    @Autowired
    RedisTemplate<String, EmployeeGetDTO> redisTemplate;
    private HashOperations<String, String, EmployeeGetDTO> hashOperations;
 
    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
  
    @Override
    public EmployeeGetDTO findEmployeeById(final String id) {
        return (EmployeeGetDTO) hashOperations.get(EMPLOYEE_CACHE, id);
    }
    
    @Override
    public void setEmployeeById(final String id, EmployeeGetDTO employeeDto) {
    	hashOperations.put(EMPLOYEE_CACHE, id, employeeDto);
        return ;
    }
 
    @Override
    public Map<String, EmployeeGetDTO> findAllEmployees() {
        return hashOperations.entries(EMPLOYEE_CACHE);
    }
 
    @Override
    public void employeeDelete(String id) {
        hashOperations.delete(EMPLOYEE_CACHE, id);
    }
}
