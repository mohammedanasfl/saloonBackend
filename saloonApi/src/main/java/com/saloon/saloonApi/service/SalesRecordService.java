package com.saloon.saloonApi.service;

import java.util.List;

import com.saloon.saloonApi.DTO.SalesRecordRequest;
import com.saloon.saloonApi.model.SalesRecord;

public interface SalesRecordService {
	
	SalesRecord createSalesRecord(SalesRecordRequest request) ;
	
	List<SalesRecord> getAllSalesRecords();
	
	SalesRecord getSalesRecordById(Integer id);
	

}
