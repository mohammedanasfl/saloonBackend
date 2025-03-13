package com.saloon.saloonApi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import com.saloon.saloonApi.DTO.SalesRecordRequest;
import com.saloon.saloonApi.exception.ServiceException;
import com.saloon.saloonApi.model.Customer;
import com.saloon.saloonApi.model.Employee;
import com.saloon.saloonApi.model.ProductList;
import com.saloon.saloonApi.model.SaleItem;
import com.saloon.saloonApi.model.SalesRecord;
import com.saloon.saloonApi.repository.CustomerRepo;
import com.saloon.saloonApi.repository.EmployeeRepo;
import com.saloon.saloonApi.repository.SalesRecordRepo;
import com.saloon.saloonApi.repository.SaloonRepository;

@Service
public class SalesRecordServiceImpl implements SalesRecordService {

    private final EmployeeRepo employeeRepo;
    private final CustomerRepo customerRepo;
    private final SaloonRepository productRepo;
    private final SalesRecordRepo salesrecordRepo;

    public SalesRecordServiceImpl(EmployeeRepo employeeRepo, CustomerRepo customerRepo, SaloonRepository productRepo,
                                  SalesRecordRepo salesrecordRepo) {
        this.employeeRepo = employeeRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.salesrecordRepo = salesrecordRepo;
    }

    @Override
    public SalesRecord createSalesRecord(SalesRecordRequest request) {
        if (request.getEmployeeId() == null || request.getCustomerPhone() == null || request.getSaleItems() == null) {
            throw new ServiceException("Invalid request: Employee ID, Customer Phone, and Sale Items are required");
        }

        Employee employee = employeeRepo.findById(request.getEmployeeId())
                .orElseThrow(() -> new ServiceException("Employee not found with ID: " + request.getEmployeeId()));

        Customer customer = customerRepo.findById(request.getCustomerPhone())
                .orElseThrow(() -> new ServiceException("Customer not found with phone: " + request.getCustomerPhone()));

        SalesRecord salesRecord = new SalesRecord();
        salesRecord.setDate(new Date());
        salesRecord.setEmployee(employee);
        salesRecord.setCustomer(customer);

        List<SaleItem> salesItems = request.getSaleItems().stream().map(item -> {
            ProductList product = productRepo.findById(item.getProductId())
                    .orElseThrow(() -> new ServiceException("Product not found with ID: " + item.getProductId()));

            return new SaleItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity()
            );

        }).collect(Collectors.toList());

        salesRecord.setSaleItems(salesItems);

        try {
            return salesrecordRepo.save(salesRecord);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Database error: Could not save sales record");
        }
    }

    @Override
    public List<SalesRecord> getAllSalesRecords() {
        List<SalesRecord> records = salesrecordRepo.findAll();
        if (records.isEmpty()) {
            throw new ServiceException("No sales records found");
        }
        return records;
    }

    @Override
    public SalesRecord getSalesRecordById(Integer id) {
        return salesrecordRepo.findById(id)
                .orElseThrow(() -> new ServiceException("SalesRecord not found with ID: " + id));
    }
}
