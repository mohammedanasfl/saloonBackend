package com.saloon.saloonApi.service;

import java.util.List;

import com.saloon.saloonApi.model.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomer();

	Customer createCustomer(Customer customer);

	Customer updateCustomer(Customer customer, Long phone);

	String deleteCustomer(Long phone);
	
	Customer getCustomerById(Long phone);

}
