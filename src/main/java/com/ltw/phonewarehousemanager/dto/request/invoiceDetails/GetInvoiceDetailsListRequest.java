package com.ltw.phonewarehousemanager.dto.request.invoiceDetails;

import com.ltw.phonewarehousemanager.constant.Constants;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class GetInvoiceDetailsListRequest {
    @Min(value = 0, message = Constants.ErrorMessageInvoiceDetailValidate.START_SIZE)
    private Integer start = 0;
    @Range(min = 5, max = 50, message = Constants.ErrorMessageInvoiceDetailValidate.LIMIT_SIZE)
    private Integer limit = 5;
    private Boolean getAll = false;
}
