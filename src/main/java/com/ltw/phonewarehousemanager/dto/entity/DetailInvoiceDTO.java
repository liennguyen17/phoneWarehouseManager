package com.ltw.phonewarehousemanager.dto.entity;

import com.ltw.phonewarehousemanager.dto.model.Invoice;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DetailInvoiceDTO {
    private Long quantity;
    private BigDecimal unitPrice;
    private StockInDetailProductDTO product;
    private BigDecimal totalAmount;
}
