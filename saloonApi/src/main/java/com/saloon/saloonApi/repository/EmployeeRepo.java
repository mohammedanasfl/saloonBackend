package com.saloon.saloonApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saloon.saloonApi.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
