package com.saloon.saloonApi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	@Id
	@Column(name = "employee_id")
	private Integer Id;
	
	@Column(name="employee_phone",unique = true,nullable = false)
	private Long phone;
	
	@Column(name="employee_name",nullable = false)
	private String name;

}
