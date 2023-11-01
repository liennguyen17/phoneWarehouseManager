package com.ltw.phonewarehousemanager.exception;

import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import lombok.Data;

import java.util.List;

@Data
public class ErrorDetailException extends RuntimeException{
    private final List<ErrorDetail> errorDetails;
    public ErrorDetailException(List<ErrorDetail> errorDetails){
        this.errorDetails = errorDetails;
    }

}
