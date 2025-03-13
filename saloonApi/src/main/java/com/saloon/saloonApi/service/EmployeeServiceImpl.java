package com.saloon.saloonApi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.saloon.saloonApi.model.Employee;
import com.saloon.saloonApi.repository.EmployeeRepo;
import com.saloon.saloonApi.exception.ServiceException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // 🔹 Check if phone number is already used
        if (employeeRepo.existsById(employee.getId())) {
            throw new ServiceException("Employee with ID " + employee.getId() + " already exists");
        }

        try {
            return employeeRepo.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Invalid data provided");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, Integer employeeId) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ServiceException("Employee not found with ID: " + employeeId));

        existingEmployee.setName(employee.getName());
        existingEmployee.setPhone(employee.getPhone());

        try {
            return employeeRepo.save(existingEmployee);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Could not update employee");
        }
    }

    @Override
    public String deleteEmployee(Integer employeeId) {
        if (!employeeRepo.existsById(employeeId)) {
            throw new ServiceException("Employee not found with ID: " + employeeId);
        }

        try {
            employeeRepo.deleteById(employeeId);
            return "Deleted successfully";
        } catch (Exception e) {
            throw new ServiceException("Error deleting employee with ID: " + employeeId);
        }
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ServiceException("Employee not found with ID: " + employeeId));
    }
}
