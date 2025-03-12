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
    public Customer updateCustomer(Customer customer, Long phone) {
        Optional<Customer> existingCustomerOpt = customerRepo.findById(phone);

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setName(customer.getName());
            return customerRepo.save(existingCustomer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + phone);
        }
    }

	@Override
	public String deleteCustomer(Long phone) {
		if (!customerRepo.existsById(phone)) {
            throw new NoSuchElementException("Product not found with id: " + phone);
        }
        customerRepo.deleteById(phone);
        return "Deleted successfully";
	}

	@Override
	public Customer getCustomerById(Long phone) {
	    return customerRepo.findById(phone)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + phone));
	}



    
}
