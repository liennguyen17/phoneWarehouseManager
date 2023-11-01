package com.ltw.phonewarehousemanager.dto.request.StockOutDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.domain.validator.date.DateValidateAnnotation;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateStockOutDetailRequest {
    @NotNull(message = Constants.ErrorMessageStockOutDetailValidate.QUANTITY_NOT_NULL)
    private Long quantityOut;
    @NotNull(message = Constants.ErrorMessageStockOutDetailValidate.PRICE_NOT_NULL)
    @DecimalMin(value = "0.01", inclusive = false, message = "Giá phải lớn hơn 0.01")
    @DecimalMax(value = "9999999999.99", message = "Giá không được lớn hơn 9999999999.99")
    private BigDecimal price;
    @NotBlank(message = Constants.ErrorMessageStockInDetailValidate.STOCK_IN_DATE_NOT_BLANK)
    @DateValidateAnnotation(message = "Định dạng ngày tháng phải là dd/mm/yyyy")
    private String stockOutDate;
    private Long product;
}
