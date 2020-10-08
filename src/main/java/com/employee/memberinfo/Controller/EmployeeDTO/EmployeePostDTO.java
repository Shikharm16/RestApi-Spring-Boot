package com.employee.memberinfo.Controller.EmployeeDTO;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.employee.memberinfo.model.customvalidation.IsEmail;

public class EmployeePostDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String eid;
	@NotBlank(message="First name can not be blank")
	private String name;
	@Size(min=2,message="Last Name must be greater than or equal to 2 characters")
	private String surname;
	@IsEmail(message="Email must be of form :: example@company.com") //custom validation
	@NotBlank(message="Must provide email")
	private String email;
	
	@Min(value=20,message="Age must be greater than 20.")
	@Max(value=60,message="Age must be less than retirement age i.e, 60")
	private int age;
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
