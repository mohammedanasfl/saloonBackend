package com.saloon.saloonApi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull(message = "Phone number cannot be null")
	@Column(name="employee_phone",unique = true,nullable = false)
	private Long phone;
	
	@NotBlank(message = "Employee name cannot be blank")
	@Column(name="employee_name",nullable = false)
	private String name;

}
