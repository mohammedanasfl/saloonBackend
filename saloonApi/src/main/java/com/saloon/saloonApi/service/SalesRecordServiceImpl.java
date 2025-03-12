package com.saloon.saloonApi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saloon.saloonApi.DTO.SalesRecordRequest;
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
public class SalesRecordServiceImpl implements SalesRecordService{
	
	private EmployeeRepo employeeRepo;
	private CustomerRepo customerRepo;
	private SaloonRepository productRepo;
	private SalesRecordRepo salesrecordRepo;
	
	public SalesRecordServiceImpl(EmployeeRepo employeeRepo, CustomerRepo customerRepo, SaloonRepository productRepo,
			SalesRecordRepo salesrecordRepo) {
		this.employeeRepo = employeeRepo;
		this.customerRepo = customerRepo;
		this.productRepo = productRepo;
		this.salesrecordRepo = salesrecordRepo;
	}

	@Override
	public SalesRecord createSalesRecord(SalesRecordRequest request) {
		Employee employee=employeeRepo.findById(request.getEmployeeId())
				.orElseThrow(()-> new RuntimeException());
		Customer customer=customerRepo.findById(request.getCustomerPhone())
				.orElseThrow(()-> new RuntimeException());
		SalesRecord salesRecord=new SalesRecord();
		
		salesRecord.setDate(new Date());
		salesRecord.setEmployee(employee);
		salesRecord.setCustomer(customer);
		
		List<SaleItem>salesItems=request.getSaleItems().stream().map(item->{
			ProductList product=productRepo.findById(item.getProductId())
					.orElseThrow(()-> new RuntimeException());
			return new SaleItem(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    item.getQuantity()
            );
					
		}).collect(Collectors.toList());
		salesRecord.setSaleItems(salesItems);
		
		
		return salesrecordRepo.save(salesRecord);
	}

	@Override
	public List<SalesRecord> getAllSalesRecords() {
		return salesrecordRepo.findAll();
	}

	@Override
	public SalesRecord getSalesRecordById(Integer id) {
		return salesrecordRepo.findById(id).orElseThrow(()-> new RuntimeException());
	}

}
