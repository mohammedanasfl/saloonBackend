package com.saloon.saloonApi.service;

import java.util.List;

import com.saloon.saloonApi.model.ProductList;

public interface SaloonService {
	
	List<ProductList> getAllProductList();
	
	ProductList createProduct(ProductList productList);
	
	ProductList upadateProduct(ProductList productList,Integer productId);
	
	String deleteProduct(Integer id);
	
}
