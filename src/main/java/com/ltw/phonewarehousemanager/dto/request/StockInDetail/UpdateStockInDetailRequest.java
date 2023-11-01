package com.ltw.phonewarehousemanager.dto.request.StockInDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.domain.validator.date.DateValidateAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateStockInDetailRequest {
    @NotNull
    private Long id;
    @NotNull(message = Constants.ErrorMessageStockInDetailValidate.QUANTITY_NOT_NULL)
    private Long quantityIn;
    @NotNull(message = Constants.ErrorMessageStockInDetailValidate.PRICE_NOT_NULL)
    private BigDecimal price;
    @NotBlank(message = Constants.ErrorMessageStockInDetailValidate.STOCK_IN_DATE_NOT_BLANK)
    @DateValidateAnnotation(message = "Định dạng ngày tháng phải là dd/mm/yyyy")
    private String stockInDate;
    private Long product;
}
