package com.employee.memberinfo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.memberinfo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee , String> {

}
