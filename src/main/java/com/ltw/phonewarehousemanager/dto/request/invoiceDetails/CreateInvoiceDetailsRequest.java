package com.ltw.phonewarehousemanager.dto.request.invoiceDetails;

import com.ltw.phonewarehousemanager.constant.Constants;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateInvoiceDetailsRequest {
    @NotNull(message = Constants.ErrorMessageInvoiceDetailValidate.QUANTITY_NOT_NULL)
    private Long quantity;
    @NotNull(message = Constants.ErrorMessageInvoiceDetailValidate.UNIT_PRICE_NOT_NULL)
    @DecimalMin(value = "0.01", inclusive = false, message = Constants.ErrorMessageInvoiceDetailValidate.UNIT_PRICE_MIN)
    @DecimalMax(value = "9999999999.99", message = Constants.ErrorMessageInvoiceDetailValidate.UNIT_PRICE_MAX)
    private BigDecimal unitPrice;
    private Long invoice;
    private Long product;

}
