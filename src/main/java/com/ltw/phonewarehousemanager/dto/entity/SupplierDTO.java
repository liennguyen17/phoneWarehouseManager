package com.ltw.phonewarehousemanager.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltw.phonewarehousemanager.constant.Constants;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class SupplierDTO {
    private Long id;
    private String name;
    private String phone;
    private String address;
    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
    private Timestamp createdTime;
    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
    private Timestamp modifiedTime;
    private String createdBy;
    private String modifiedBy;

}
