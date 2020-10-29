package com.employee.memberinfo.Controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.memberinfo.Controller.EmployeeDTO.EmployeeGetDTO;
import com.employee.memberinfo.Redis.RedisService;
import com.employee.memberinfo.service.EmployeeService;

@RestController
@RequestMapping("employee")
public class ControllerForRedisRequest {

	@Autowired
	RedisService redisservice;
	
	@Autowired
    RedisTemplate<String, EmployeeGetDTO> redisTemplate;
	
	@Autowired
	EmployeeService service;
	
	private final String EMPLOYEE_CACHE = "employee";
	private HashOperations<String, String, EmployeeGetDTO> hashOperations;
	
	@PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }
	
	@GetMapping("page")
	public String Getpage() {
		return "login";
	}
	
	@GetMapping("redisentry")
	public Map<String, EmployeeGetDTO> getRedisEntries() {
		return redisservice.findAllEmployees();
	}
	
	@GetMapping("redis/{id}")
	public EmployeeGetDTO GetRedisEmployeeById(@PathVariable String id) {
		
		if(hashOperations.hasKey(EMPLOYEE_CACHE, id))
		{
			return redisservice.findEmployeeById(id);
		}
		EmployeeGetDTO fetchDtoemployeebyid = service.fetchDtoemployeebyid(id);
		redisservice.setEmployeeById(id,fetchDtoemployeebyid);
		
		return fetchDtoemployeebyid;
	}
	
	@DeleteMapping("redis/{id}")
	public ResponseEntity<String> DeleteEmployeeById(@PathVariable String id) {
				
		redisservice.employeeDelete(id);

		return new ResponseEntity<String>("Employee Deleted from manual Redis!", HttpStatus.ACCEPTED);
	}
}
