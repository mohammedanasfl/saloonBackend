package com.saloon.saloonApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saloon.saloonApi.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
