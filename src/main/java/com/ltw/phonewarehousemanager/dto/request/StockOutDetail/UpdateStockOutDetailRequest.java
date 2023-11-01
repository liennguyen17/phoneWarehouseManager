package com.ltw.phonewarehousemanager.dto.request.StockOutDetail;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateStockOutDetailRequest {
    private Long id;
    private Long quantityOut;
    private BigDecimal price;
    private String stockOutDate;
    private Long product;
}
