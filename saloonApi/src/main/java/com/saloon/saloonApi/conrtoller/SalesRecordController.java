package com.saloon.saloonApi.conrtoller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saloon.saloonApi.DTO.SalesRecordRequest;
import com.saloon.saloonApi.model.SalesRecord;
import com.saloon.saloonApi.service.SalesRecordService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/sales")
public class SalesRecordController {
	
	private final SalesRecordService salesRecordService;

    public SalesRecordController(SalesRecordService salesRecordService) {
        this.salesRecordService = salesRecordService;
    }

    //  Create a new sales record
    @PostMapping
    public ResponseEntity<SalesRecord> createSalesRecord(@Valid @RequestBody SalesRecordRequest request) {
        SalesRecord salesRecord = salesRecordService.createSalesRecord(request);
        return ResponseEntity.ok(salesRecord);
    }

    //  Get all sales records
    @GetMapping
    public ResponseEntity<List<SalesRecord>> getAllSalesRecords() {
        List<SalesRecord> salesRecords = salesRecordService.getAllSalesRecords();
        return ResponseEntity.ok(salesRecords);
    }

    //  Get a sales record by ID
    @GetMapping("/{id}")
    public ResponseEntity<SalesRecord> getSalesRecordById(@PathVariable Integer id) {
        SalesRecord salesRecord = salesRecordService.getSalesRecordById(id);
        return ResponseEntity.ok(salesRecord);
    }

}
