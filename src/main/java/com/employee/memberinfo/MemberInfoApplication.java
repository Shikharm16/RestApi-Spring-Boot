package com.employee.memberinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MemberInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberInfoApplication.class, args);
	}

}
