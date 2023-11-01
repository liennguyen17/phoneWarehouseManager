package com.ltw.phonewarehousemanager.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.model.Role;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String password;
    private String address;
    private String email;
    private String phone;
    private RoleDTO role;
    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
    private Timestamp createdTime;
    @JsonFormat(pattern = Constants.DateTimeFormatConstant.DATE_TIME_FORMAT, timezone = Constants.DateTimeFormatConstant.TIME_ZONE)
    private Timestamp modifiedTime;
    private String createdBy;
    private String modifiedBy;
}
