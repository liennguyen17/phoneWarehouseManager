package com.ltw.phonewarehousemanager.dto.request.product;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.model.InvoiceDetail;
import com.ltw.phonewarehousemanager.dto.model.StockOutDetail;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.dto.model.Warehouse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CreateProductRequest {
    @Size(max = 200, message = Constants.ErrorMessageProductValidation.NAME_SIZE)
    @NotBlank(message = Constants.ErrorMessageProductValidation.NAME_NOT_BLANK)
    private String name;
    @Size(max = 500, message = Constants.ErrorMessageProductValidation.DESCRIPTION_SIZE)
    @NotBlank(message = Constants.ErrorMessageProductValidation.DESCRIPTION_NOT_BLANK)
    private String description;
    private Long supplier;


}
