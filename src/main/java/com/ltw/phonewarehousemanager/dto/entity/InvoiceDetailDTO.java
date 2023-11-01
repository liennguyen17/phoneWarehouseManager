package com.ltw.phonewarehousemanager.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.model.Invoice;
import com.ltw.phonewarehousemanager.dto.model.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class InvoiceDetailDTO {
    private Long id;
    private Long quantity;
    private BigDecimal unitPrice;
    private Invoice invoice;
    private StockInDetailProductDTO product;
    private BigDecimal totalAmount;
    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
    private Timestamp createdTime;
//    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
//    private Timestamp modifiedTime;
    private String createdBy;
//    private String modifiedBy;
}
