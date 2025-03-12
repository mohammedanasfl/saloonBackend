package com.saloon.saloonApi.DTO;

import java.util.List;

import com.saloon.saloonApi.model.SaleItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesRecordRequest {
    private Integer employeeId;
    private Long customerPhone;
    private List<SaleItem> saleItems;
}
