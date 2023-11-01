package com.ltw.phonewarehousemanager.dto.request.supplier;

import com.ltw.phonewarehousemanager.constant.Constants;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class DeleteSupplierRequest {
    @NotEmpty(message = Constants.ErrorMessageSupplierValidation.DELETE_NOT_EMPTY)
    private List<Long> ids;
}
