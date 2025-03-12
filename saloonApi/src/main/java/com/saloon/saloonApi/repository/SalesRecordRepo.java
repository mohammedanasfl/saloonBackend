package com.saloon.saloonApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saloon.saloonApi.model.SalesRecord;

@Repository
public interface SalesRecordRepo extends JpaRepository<SalesRecord, Integer>{

}
