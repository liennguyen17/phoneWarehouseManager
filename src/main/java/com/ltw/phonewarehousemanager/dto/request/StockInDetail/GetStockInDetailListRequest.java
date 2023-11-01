package com.ltw.phonewarehousemanager.dto.request.StockInDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class GetStockInDetailListRequest {
    @Min(value = 0, message = Constants.ErrorMessageStockInDetailValidate.START_SIZE)
    private Integer start = 0;
    @Range(min = 5, max = 50, message = Constants.ErrorMessageStockInDetailValidate.LIMIT_SIZE)
    private Integer limit = 5;
    private Boolean getAll = false;
}
