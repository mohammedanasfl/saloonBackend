package com.saloon.saloonApi.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {

	private Integer productId;
	private String productName;
	private Double productPrice;
	private Integer quantity;

}
