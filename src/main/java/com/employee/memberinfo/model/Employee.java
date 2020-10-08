package com.employee.memberinfo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.employee.memberinfo.model.customvalidation.IsEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@GeneratedValue(generator = "uuid4")
	@GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name="Id")
	@Id
	@JsonIgnore
	private String id;
	
	
	@NotBlank(message="First name can not be blank")
	@Column(name="FirstName")
	private String firstName;
	
	
	@Size(min=2,message="Last Name must be greater than or equal to 2 characters")
	@Column(name="LastName")
	private String lastName;
	
	
	@Min(value=20,message="Age must be greater than 20.")
	@Max(value=60,message="Age must be less than retirement age i.e, 60")
	private int age;

	
	@IsEmail(message="Email must be of form :: example@company.com") //custom validation
	@NotBlank(message="Must provide email")
	@JsonIgnore
	@Column(name="Email")
	private String email;
	
	@JsonIgnore
	public String getEmail() {
		return email;
	}
	
	@JsonProperty
	public void setEmail(String email) {
		this.email = email;
	}
	

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Employee [ Name=" + firstName + " " + lastName + ", Age=" + age + ", Eamil=" + email + "]";
	}	
	
}
