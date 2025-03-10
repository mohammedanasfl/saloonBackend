package com.saloon.saloonApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saloon.saloonApi.model.Employee;
import com.saloon.saloonApi.repository.EmployeeRepo;

import org.springframework.http.HttpStatus;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee, Integer employeeId) {
		Optional<Employee> existingEmployeeOpt = employeeRepo.findById(employeeId);

		if (existingEmployeeOpt.isPresent()) {
			Employee existingEmployee = existingEmployeeOpt.get();
			existingEmployee.setName(employee.getName());
			existingEmployee.setPhone(employee.getPhone());
			return employeeRepo.save(existingEmployee);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with phone: " + employeeId);
		}
	}

	@Override
	public String deleteEmployee(Integer employeeId) {
		if (!employeeRepo.existsById(employeeId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with phone: " + employeeId);
		}
		employeeRepo.deleteById(employeeId);
		return "Deleted successfully";
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		return employeeRepo.findById(employeeId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + employeeId));
	}

}
