package com.ltw.phonewarehousemanager.dto.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockInDetailUpdateDTO {
    private Long id;
    private Long quantityIn;
    private BigDecimal price;
    private String stockInDate;

}
