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
@Table(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer Id;

	
	@Column(name="customer_phone",nullable = false,unique = true)
	private Long phone;
	
	@Column(name="customer_name",nullable = false)
	private String name;
	
	@Column(name="customer_address",nullable = false)
	private String address;

}
