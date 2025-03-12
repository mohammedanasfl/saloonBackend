package com.saloon.saloonApi.model;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_list")
public class ProductList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer Id;
	
	@Column(name="name",nullable=false, unique = true)
	private String name;
	
	@Column(name="price",nullable=false)
	private Double price;
	
	@ElementCollection
    @CollectionTable(name = "sale_items", joinColumns = @JoinColumn(name = "sales_record_id"))
    private List<SaleItem> saleItems;
	
	
	
	

}
