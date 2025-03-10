package com.saloon.saloonApi.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.saloon.saloonApi.model.Customer;
import com.saloon.saloonApi.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Integer customerId) {
        Optional<Customer> existingCustomerOpt = customerRepo.findById(customerId);

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());
            return customerRepo.save(existingCustomer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + customerId);
        }
    }

	@Override
	public String deleteCustomer(Integer customerId) {
		if (!customerRepo.existsById(customerId)) {
            throw new NoSuchElementException("Product not found with id: " + customerId);
        }
        customerRepo.deleteById(customerId);
        return "Deleted successfully";
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
	    return customerRepo.findById(customerId)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + customerId));
	}



    
}
