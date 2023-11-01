package com.ltw.phonewarehousemanager.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltw.phonewarehousemanager.constant.Constants;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Data
public class InvoiceDTO {
    private Long id;
//    private String userId;//nào có user thì truyền vào
    private List<DetailInvoiceDTO> invoiceDetails;
    private BigDecimal totalAmount;
    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
    private Timestamp createdTime;
    private String createdBy;


}
