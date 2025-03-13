package com.saloon.saloonApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.saloon.saloonApi.model.Customer;
import com.saloon.saloonApi.repository.CustomerRepo;
import com.saloon.saloonApi.exception.ServiceException;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        // 🔹 Check if phone number already exists
        if (customerRepo.existsById(customer.getPhone())) {
            throw new ServiceException("Customer with phone " + customer.getPhone() + " already exists");
        }

        try {
            return customerRepo.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Invalid data provided");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer, Long phone) {
        Customer existingCustomer = customerRepo.findById(phone)
                .orElseThrow(() -> new ServiceException("Customer not found with phone: " + phone));

        existingCustomer.setName(customer.getName());

        try {
            return customerRepo.save(existingCustomer);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Could not update customer");
        }
    }

    @Override
    public String deleteCustomer(Long phone) {
        if (!customerRepo.existsById(phone)) {
            throw new ServiceException("Customer not found with phone: " + phone);
        }

        try {
            customerRepo.deleteById(phone);
            return "Deleted successfully";
        } catch (Exception e) {
            throw new ServiceException("Error deleting customer with phone: " + phone);
        }
    }

    @Override
    public Customer getCustomerById(Long phone) {
        return customerRepo.findById(phone)
                .orElseThrow(() -> new ServiceException("Customer not found with phone: " + phone));
    }
}
