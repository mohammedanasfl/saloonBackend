package com.saloon.saloonApi.service;

import java.util.List;

import com.saloon.saloonApi.model.Employee;

public interface EmployeeService {
		
		List<Employee> getAllEmployee();

		Employee createEmployee(Employee employee);

		Employee updateEmployee(Employee employee, Integer employeeId);

		String deleteEmployee(Integer employeeId);
		
		Employee getEmployeeById(Integer employeeId);




}
