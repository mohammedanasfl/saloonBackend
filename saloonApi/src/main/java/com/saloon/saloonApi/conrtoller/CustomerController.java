package com.saloon.saloonApi.conrtoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saloon.saloonApi.model.Customer;
import com.saloon.saloonApi.service.CustomerService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PATCH, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT}, allowedHeaders = "*")
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Fetch all customers
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomer();
        return ResponseEntity.ok(customers);
    }

    // Create a new customer
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    // Get a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable  Long phone) {
        Customer customer = customerService.getCustomerById(phone);
        return ResponseEntity.ok(customer);
    }

    // Update a customer
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long phone) {
        Customer updatedCustomer = customerService.updateCustomer(customer, phone);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Delete a customer
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long phone) {
        try {
            String response = customerService.deleteCustomer(phone);
            return ResponseEntity.ok(Map.of("message", response, "status", HttpStatus.OK));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", e.getReason(), "phone", phone, "status", e.getStatusCode()));
        }
    }
}
