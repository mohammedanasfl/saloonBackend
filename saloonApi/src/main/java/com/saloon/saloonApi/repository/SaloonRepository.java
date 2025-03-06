package com.saloon.saloonApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saloon.saloonApi.model.ProductList;
@Repository
public interface SaloonRepository extends JpaRepository<ProductList,Integer> {

}
