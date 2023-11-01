package com.ltw.phonewarehousemanager.dto.request.StockInDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.domain.validator.date.DateValidateAnnotation;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CreateStockInDetailRequest {
    @NotNull(message = Constants.ErrorMessageStockInDetailValidate.QUANTITY_NOT_NULL)
    private Long quantityIn;
    @NotNull(message = Constants.ErrorMessageStockInDetailValidate.PRICE_NOT_NULL)
    @DecimalMin(value = "0.01", inclusive = false, message = Constants.ErrorMessageStockInDetailValidate.PRICE_MIN)
    @DecimalMax(value = "999999999999.99", message = Constants.ErrorMessageStockInDetailValidate.PRICE_MAX)
    private BigDecimal price;
    @NotBlank(message = Constants.ErrorMessageStockInDetailValidate.STOCK_IN_DATE_NOT_BLANK)
    @DateValidateAnnotation(message = Constants.DateTimeFormatConstant.FORMAT_ERROR)
    private String stockInDate;
    private Long product;
    private Long warehouse = 1L;
}
